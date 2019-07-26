package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.io.IOException;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity2 extends AppCompatActivity {
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private CameraSource cameraSource = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding2);
        preview = (CameraSourcePreview) findViewById(R.id.cameraPreview);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        cameraSource = new CameraSource(this, graphicOverlay);
        //Back camera
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
        cameraSource.setMachineLearningFrameProcessor(new BarcodeScanningProcessor());
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

    public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

        private static final String TAG = "BarcodeScanProc";

        private final FirebaseVisionBarcodeDetector detector;

        public BarcodeScanningProcessor() {
            // Note that if you know which format of barcode your app is dealing with, detection will be
            // faster to specify the supported barcode formats one by one, e.g.
            // new FirebaseVisionBarcodeDetectorOptions.Builder()
            //     .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
            //     .build();
            detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
        }

        @Override
        public void stop() {
            try {
                detector.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
            }
        }

        @Override
        protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
            return detector.detectInImage(image);
        }

        @Override
        protected void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionBarcode> barcodes, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            if (originalCameraImage != null) {
                CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
                graphicOverlay.add(imageGraphic);
            }
            for (int i = 0; i < barcodes.size(); ++i) {
                FirebaseVisionBarcode barcode = barcodes.get(i);
                BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
                graphicOverlay.add(barcodeGraphic);
            }
            graphicOverlay.postInvalidate();
        }

        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.e(TAG, "Barcode detection failed " + e);
        }
    }

    public class BarcodeGraphic extends GraphicOverlay.Graphic {

        private static final int TEXT_COLOR = Color.WHITE;
        private static final float TEXT_SIZE = 54.0f;
        private static final float STROKE_WIDTH = 4.0f;

        private final Paint rectPaint;
        private final Paint barcodePaint;
        private final FirebaseVisionBarcode barcode;

        BarcodeGraphic(GraphicOverlay overlay, FirebaseVisionBarcode barcode) {
            super(overlay);

            this.barcode = barcode;

            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(STROKE_WIDTH);

            barcodePaint = new Paint();
            barcodePaint.setColor(TEXT_COLOR);
            barcodePaint.setTextSize(TEXT_SIZE);
        }

        /**
         * Draws the barcode block annotations for position, size, and raw value on the supplied canvas.
         */
        @Override
        public void draw(Canvas canvas) {
            if (barcode == null) {
                throw new IllegalStateException("Attempting to draw a null barcode.");
            }

            // Draws the bounding box around the BarcodeBlock.
            RectF rect = new RectF(barcode.getBoundingBox());
            rect.left = translateX(rect.left);
            rect.top = translateY(rect.top);
            rect.right = translateX(rect.right);
            rect.bottom = translateY(rect.bottom);
            canvas.drawRect(rect, rectPaint);

            // Renders the barcode at the bottom of the box.
            canvas.drawText(barcode.getRawValue(), rect.left, rect.bottom, barcodePaint);
        }
    }
}
