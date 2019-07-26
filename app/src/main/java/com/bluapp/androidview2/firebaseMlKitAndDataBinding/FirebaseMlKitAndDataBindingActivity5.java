package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.IOException;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity5 extends AppCompatActivity {
    private Button getImageButton;
    private ImageView cameraPreview;
    private GraphicOverlay graphicOverlay;
    private static final int REQUEST_IMAGE_CAPTURE = 1001;
    private Uri imageUri;
    private Integer imageMaxWidth;
    private Integer imageMaxHeight;
    private boolean isLandScape;
    private Bitmap bitmapForDetection;
    private VisionImageProcessor imageProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding5);
        getImageButton = (Button)findViewById(R.id.getImageButton);
        cameraPreview = (ImageView) findViewById(R.id.cameraPreview);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        isLandScape = (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
        getImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCameraIntentForResult();
            }
        });

    }

    private void startCameraIntentForResult() {
        // Clean up last time's image
        imageUri = null;
        cameraPreview.setImageBitmap(null);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
            imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void tryReloadAndDetectInImage() {
        try {
            if (imageUri == null) {
                return;
            }
            // Clear the overlay first
            graphicOverlay.clear();
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            // Get the dimensions of the View
            Pair<Integer, Integer> targetedSize = getTargetedWidthHeight();
            int targetWidth = targetedSize.first;
            int maxHeight = targetedSize.second;
            // Determine how much to scale down the image
            float scaleFactor = Math.max((float) imageBitmap.getWidth() / (float) targetWidth, (float) imageBitmap.getHeight() / (float) maxHeight);
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, (int) (imageBitmap.getWidth() / scaleFactor), (int) (imageBitmap.getHeight() / scaleFactor), true);
            cameraPreview.setImageBitmap(resizedBitmap);
            bitmapForDetection = resizedBitmap;
            imageProcessor = new CloudLandmarkRecognitionProcessor();
            imageProcessor.process(bitmapForDetection, graphicOverlay);
        } catch (IOException e) {
            Log.e("AndroidView2", "Error retrieving saved image");
        }
    }

    private Pair<Integer, Integer> getTargetedWidthHeight() {
        int targetWidth;
        int targetHeight;
                int maxWidthForPortraitMode = getImageMaxWidth();
                int maxHeightForPortraitMode = getImageMaxHeight();
                targetWidth = isLandScape ? maxHeightForPortraitMode : maxWidthForPortraitMode;
                targetHeight = isLandScape ? maxWidthForPortraitMode : maxHeightForPortraitMode;
        return new Pair<>(targetWidth, targetHeight);
    }

    private Integer getImageMaxWidth() {
        if (imageMaxWidth == null) {
            if (isLandScape) {
                imageMaxWidth = ((View) cameraPreview.getParent()).getHeight() - findViewById(R.id.getImageButton).getHeight();
            } else {
                imageMaxWidth = ((View) cameraPreview.getParent()).getWidth();
            }
        }
        return imageMaxWidth;
    }

    private Integer getImageMaxHeight() {
        if (imageMaxHeight == null) {
            if (isLandScape) {
                imageMaxHeight = ((View) cameraPreview.getParent()).getWidth();
            } else {
                imageMaxHeight = ((View) cameraPreview.getParent()).getHeight() - findViewById(R.id.getImageButton).getHeight();
            }
        }
        return imageMaxHeight;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            tryReloadAndDetectInImage();
        }
    }

    public class CloudLandmarkRecognitionProcessor extends VisionProcessorBase<List<FirebaseVisionCloudLandmark>> {
        private static final String TAG = "CloudLmkRecProc";
        private final FirebaseVisionCloudLandmarkDetector detector;

        public CloudLandmarkRecognitionProcessor() {
            super();
            FirebaseVisionCloudDetectorOptions options = new FirebaseVisionCloudDetectorOptions.Builder()
                            .setMaxResults(10)
                            .setModelType(FirebaseVisionCloudDetectorOptions.STABLE_MODEL)
                            .build();
            detector = FirebaseVision.getInstance().getVisionCloudLandmarkDetector(options);
        }

        @Override
        protected Task<List<FirebaseVisionCloudLandmark>> detectInImage(FirebaseVisionImage image) {
            return detector.detectInImage(image);
        }

        @Override
        protected void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionCloudLandmark> landmarks, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            Log.d(TAG, "cloud landmark size: " + landmarks.size());
            for (int i = 0; i < landmarks.size(); ++i) {
                FirebaseVisionCloudLandmark landmark = landmarks.get(i);
                Log.d(TAG, "cloud landmark: " + landmark);
                CloudLandmarkGraphic cloudLandmarkGraphic = new CloudLandmarkGraphic(graphicOverlay, landmark);
                graphicOverlay.add(cloudLandmarkGraphic);
            }
            graphicOverlay.postInvalidate();
        }

        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.e(TAG, "Cloud Landmark detection failed " + e);
        }
    }

    public class CloudLandmarkGraphic extends GraphicOverlay.Graphic {
        private static final int TEXT_COLOR = Color.WHITE;
        private static final float TEXT_SIZE = 54.0f;
        private static final float STROKE_WIDTH = 4.0f;
        private final Paint rectPaint;
        private final Paint landmarkPaint;
        private FirebaseVisionCloudLandmark landmark;

        CloudLandmarkGraphic(GraphicOverlay overlay, FirebaseVisionCloudLandmark landmark) {
            super(overlay);
            this.landmark = landmark;
            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(STROKE_WIDTH);
            landmarkPaint = new Paint();
            landmarkPaint.setColor(TEXT_COLOR);
            landmarkPaint.setTextSize(TEXT_SIZE);
        }
        /**
         * Draws the landmark block annotations for position, size, and raw value on the supplied canvas.
         */
        @Override
        public void draw(Canvas canvas) {
            if (landmark == null) {
                throw new IllegalStateException("Attempting to draw a null landmark.");
            }
            if (landmark.getLandmark() == null || landmark.getBoundingBox() == null) {
                return;
            }
            // Draws the bounding box around the LandmarkBlock.
            RectF rect = new RectF(landmark.getBoundingBox());
            rect.left = translateX(rect.left);
            rect.top = translateY(rect.top);
            rect.right = translateX(rect.right);
            rect.bottom = translateY(rect.bottom);
            canvas.drawRect(rect, rectPaint);
            // Renders the landmark at the bottom of the box.
            canvas.drawText(landmark.getLandmark(), rect.left, rect.bottom, landmarkPaint);
        }
    }
}
