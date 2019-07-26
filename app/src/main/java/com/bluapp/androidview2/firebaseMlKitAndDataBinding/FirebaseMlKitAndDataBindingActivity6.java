package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;

import java.io.IOException;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity6 extends AppCompatActivity {
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private CameraSource cameraSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding6);
        preview = (CameraSourcePreview) findViewById(R.id.cameraPreview);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        cameraSource = new CameraSource(this, graphicOverlay);
        //Back camera
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
        cameraSource.setMachineLearningFrameProcessor(new ImageLabelingProcessor());
        startCameraSource();
    }

    private void startCameraSource() {
        if (cameraSource != null) {
            try {
                if (preview == null) {
                    Log.d("AndroidView2", "resume: Preview is null");
                }
                if (graphicOverlay == null) {
                    Log.d("AndroidView2", "resume: graphOverlay is null");
                }
                preview.start(cameraSource, graphicOverlay);
            } catch (IOException e) {
                Log.e("AndroidView2", "Unable to start camera source.", e);
                cameraSource.release();
                cameraSource = null;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    @Override
    protected void onPause() {
        super.onPause();
        preview.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    public class ImageLabelingProcessor extends VisionProcessorBase<List<FirebaseVisionImageLabel>> {
        private static final String TAG = "ImageLabelingProcessor";
        private final FirebaseVisionImageLabeler detector;

        public ImageLabelingProcessor() {
            detector = FirebaseVision.getInstance().getOnDeviceImageLabeler();
        }
        @Override
        public void stop() {
            try {
                detector.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception thrown while trying to close Text Detector: " + e);
            }
        }

        @Override
        protected Task<List<FirebaseVisionImageLabel>> detectInImage(FirebaseVisionImage image) {
            return detector.processImage(image);
        }

        @Override
        protected void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionImageLabel> labels, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            if (originalCameraImage != null) {
                CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
                graphicOverlay.add(imageGraphic);
            }
            LabelGraphic labelGraphic = new LabelGraphic(graphicOverlay, labels);
            graphicOverlay.add(labelGraphic);
            graphicOverlay.postInvalidate();
        }

        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.w(TAG, "Label detection failed." + e);
        }
    }

    public class LabelGraphic extends GraphicOverlay.Graphic {
        private final Paint textPaint;
        private final GraphicOverlay overlay;

        private final List<FirebaseVisionImageLabel> labels;
        LabelGraphic(GraphicOverlay overlay, List<FirebaseVisionImageLabel> labels) {
            super(overlay);
            this.overlay = overlay;
            this.labels = labels;
            textPaint = new Paint();
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(60.0f);
        }
        @Override
        public synchronized void draw(Canvas canvas) {
            float x = overlay.getWidth() / 4.0f;
            float y = overlay.getHeight() / 2.0f;

            for (FirebaseVisionImageLabel label : labels) {
                canvas.drawText(label.getText(), x, y, textPaint);
                y = y - 62.0f;
            }
        }
    }
}
