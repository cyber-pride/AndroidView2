package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.io.IOException;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity4 extends AppCompatActivity {
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private CameraSource cameraSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding4);
        preview = (CameraSourcePreview) findViewById(R.id.cameraPreview);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        cameraSource = new CameraSource(this, graphicOverlay);
        //Back camera
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
        cameraSource.setMachineLearningFrameProcessor(new FaceDetectionProcessor(getResources()));
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

    public class FaceDetectionProcessor extends VisionProcessorBase<List<FirebaseVisionFace>> {

        private static final String TAG = "FaceDetectionProcessor";

        private final FirebaseVisionFaceDetector detector;
        private final Bitmap overlayBitmap;

        public FaceDetectionProcessor(Resources resources) {
            FirebaseVisionFaceDetectorOptions options = new FirebaseVisionFaceDetectorOptions.Builder()
                            .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                            .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                            .build();

            detector = FirebaseVision.getInstance().getVisionFaceDetector(options);

            overlayBitmap = BitmapFactory.decodeResource(resources, R.drawable.clown_nose);
        }

        @Override
        public void stop() {
            try {
                detector.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception thrown while trying to close Face Detector: " + e);
            }
        }

        @Override
        protected Task<List<FirebaseVisionFace>> detectInImage(FirebaseVisionImage image) {
            return detector.detectInImage(image);
        }

        @Override
        protected void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionFace> faces, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            if (originalCameraImage != null) {
                CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
                graphicOverlay.add(imageGraphic);
            }
            for (int i = 0; i < faces.size(); ++i) {
                FirebaseVisionFace face = faces.get(i);

                int cameraFacing = frameMetadata != null ? frameMetadata.getCameraFacing() : Camera.CameraInfo.CAMERA_FACING_BACK;
                FaceGraphic faceGraphic = new FaceGraphic(graphicOverlay, face, cameraFacing, overlayBitmap);
                graphicOverlay.add(faceGraphic);
            }
            graphicOverlay.postInvalidate();
        }

        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.e(TAG, "Face detection failed " + e);
        }
    }

    public class FaceGraphic extends GraphicOverlay.Graphic {
        private static final float FACE_POSITION_RADIUS = 4.0f;
        private static final float ID_TEXT_SIZE = 30.0f;
        private static final float ID_Y_OFFSET = 50.0f;
        private static final float ID_X_OFFSET = -50.0f;
        private static final float BOX_STROKE_WIDTH = 5.0f;

        private int facing;

        private final Paint facePositionPaint;
        private final Paint idPaint;
        private final Paint boxPaint;

        private volatile FirebaseVisionFace firebaseVisionFace;

        private final Bitmap overlayBitmap;

        public FaceGraphic(GraphicOverlay overlay, FirebaseVisionFace face, int facing, Bitmap overlayBitmap) {
            super(overlay);
            firebaseVisionFace = face;
            this.facing = facing;
            this.overlayBitmap = overlayBitmap;
            final int selectedColor = Color.WHITE;
            facePositionPaint = new Paint();
            facePositionPaint.setColor(selectedColor);
            idPaint = new Paint();
            idPaint.setColor(selectedColor);
            idPaint.setTextSize(ID_TEXT_SIZE);
            boxPaint = new Paint();
            boxPaint.setColor(selectedColor);
            boxPaint.setStyle(Paint.Style.STROKE);
            boxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        }
        /**
         * Draws the face annotations for position on the supplied canvas.
         */
        @Override
        public void draw(Canvas canvas) {
            FirebaseVisionFace face = firebaseVisionFace;
            if (face == null) {
                return;
            }
            // Draws a circle at the position of the detected face, with the face's track id below.
            // An offset is used on the Y axis in order to draw the circle, face id and happiness level in the top area
            // of the face's bounding box
            float x = translateX(face.getBoundingBox().centerX());
            float y = translateY(face.getBoundingBox().centerY());
            canvas.drawCircle(x, y - 4 * ID_Y_OFFSET, FACE_POSITION_RADIUS, facePositionPaint);
            canvas.drawText("id: " + face.getTrackingId(), x + ID_X_OFFSET, y - 3 * ID_Y_OFFSET, idPaint);
            canvas.drawText("happiness: " + String.format("%.2f", face.getSmilingProbability()), x + ID_X_OFFSET * 3, y - 2 * ID_Y_OFFSET, idPaint);
            if (facing == CameraSource.CAMERA_FACING_FRONT) {
                canvas.drawText("right eye: " + String.format("%.2f", face.getRightEyeOpenProbability()), x - ID_X_OFFSET, y, idPaint);
                canvas.drawText("left eye: " + String.format("%.2f", face.getLeftEyeOpenProbability()), x + ID_X_OFFSET * 6, y, idPaint);
            } else {
                canvas.drawText("left eye: " + String.format("%.2f", face.getLeftEyeOpenProbability()), x - ID_X_OFFSET, y, idPaint);
                canvas.drawText("right eye: " + String.format("%.2f", face.getRightEyeOpenProbability()), x + ID_X_OFFSET * 6, y, idPaint);
            }
            // Draws a bounding box around the face.
            float xOffset = scaleX(face.getBoundingBox().width() / 2.0f);
            float yOffset = scaleY(face.getBoundingBox().height() / 2.0f);
            float left = x - xOffset;
            float top = y - yOffset;
            float right = x + xOffset;
            float bottom = y + yOffset;
            canvas.drawRect(left, top, right, bottom, boxPaint);
            // draw landmarks
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.MOUTH_BOTTOM);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.LEFT_CHEEK);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.LEFT_EAR);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.MOUTH_LEFT);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.LEFT_EYE);
            drawBitmapOverLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.NOSE_BASE);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.RIGHT_CHEEK);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.RIGHT_EAR);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.RIGHT_EYE);
            drawLandmarkPosition(canvas, face, FirebaseVisionFaceLandmark.MOUTH_RIGHT);
        }

        private void drawLandmarkPosition(Canvas canvas, FirebaseVisionFace face, int landmarkID) {
            FirebaseVisionFaceLandmark landmark = face.getLandmark(landmarkID);
            if (landmark != null) {
                FirebaseVisionPoint point = landmark.getPosition();
                canvas.drawCircle(translateX(point.getX()), translateY(point.getY()), 10f, idPaint);
            }
        }

        private void drawBitmapOverLandmarkPosition(Canvas canvas, FirebaseVisionFace face, int landmarkID) {
            FirebaseVisionFaceLandmark landmark = face.getLandmark(landmarkID);
            if (landmark == null) {
                return;
            }
            FirebaseVisionPoint point = landmark.getPosition();
            if (overlayBitmap != null) {
                float imageEdgeSizeBasedOnFaceSize = (face.getBoundingBox().width() / 4.0f);
                int left = (int) (translateX(point.getX()) - imageEdgeSizeBasedOnFaceSize);
                int top = (int) (translateY(point.getY()) - imageEdgeSizeBasedOnFaceSize);
                int right = (int) (translateX(point.getX()) + imageEdgeSizeBasedOnFaceSize);
                int bottom = (int) (translateY(point.getY()) + imageEdgeSizeBasedOnFaceSize);
                canvas.drawBitmap(overlayBitmap, null, new Rect(left, top, right, bottom), null);
            }
        }
    }
}
