package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;
import java.util.List;

public class FirebaseMlKitAndDataBindingActivity1 extends AppCompatActivity {
    private CameraSourcePreview preview;
    private GraphicOverlay graphicOverlay;
    private CameraSource cameraSource = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding1);
        preview = (CameraSourcePreview) findViewById(R.id.cameraPreview);
        graphicOverlay = (GraphicOverlay) findViewById(R.id.graphicOverlay);
        cameraSource = new CameraSource(this, graphicOverlay);
        //Back camera
        cameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);
        cameraSource.setMachineLearningFrameProcessor(new TextRecognitionProcessor());
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

    public class TextRecognitionProcessor extends VisionProcessorBase<FirebaseVisionText> {

        private static final String TAG = "TextRecProc";

        private final FirebaseVisionTextRecognizer detector;

        public TextRecognitionProcessor() {
            detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
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
        protected Task<FirebaseVisionText> detectInImage(FirebaseVisionImage image) {
            return detector.processImage(image);
        }

        @Override
        protected void onSuccess(@Nullable Bitmap originalCameraImage,@NonNull FirebaseVisionText results, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
            graphicOverlay.clear();
            if (originalCameraImage != null) {
                CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
                graphicOverlay.add(imageGraphic);
            }
            List<FirebaseVisionText.TextBlock> blocks = results.getTextBlocks();
            for (int i = 0; i < blocks.size(); i++) {
                List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
                for (int j = 0; j < lines.size(); j++) {
                    List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                    for (int k = 0; k < elements.size(); k++) {
                        GraphicOverlay.Graphic textGraphic = new TextGraphic(graphicOverlay, elements.get(k));
                        graphicOverlay.add(textGraphic);
                    }
                }
            }
            graphicOverlay.postInvalidate();
        }

        @Override
        protected void onFailure(@NonNull Exception e) {
            Log.w(TAG, "Text detection failed." + e);
        }
    }

    public class TextGraphic extends GraphicOverlay.Graphic {

        private static final int TEXT_COLOR = Color.WHITE;
        private static final float TEXT_SIZE = 54.0f;
        private static final float STROKE_WIDTH = 4.0f;

        private final Paint rectPaint;
        private final Paint textPaint;
        private final FirebaseVisionText.Element text;

        TextGraphic(GraphicOverlay overlay, FirebaseVisionText.Element text) {
            super(overlay);

            this.text = text;

            rectPaint = new Paint();
            rectPaint.setColor(TEXT_COLOR);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(STROKE_WIDTH);

            textPaint = new Paint();
            textPaint.setColor(TEXT_COLOR);
            textPaint.setTextSize(TEXT_SIZE);
        }

        /** Draws the text block annotations for position, size, and raw value on the supplied canvas. */
        @Override
        public void draw(Canvas canvas) {
            if (text == null) {
                throw new IllegalStateException("Attempting to draw a null text.");
            }
            // Draws the bounding box around the TextBlock.
            RectF rect = new RectF(text.getBoundingBox());
            rect.left = translateX(rect.left);
            rect.top = translateY(rect.top);
            rect.right = translateX(rect.right);
            rect.bottom = translateY(rect.bottom);
            canvas.drawRect(rect, rectPaint);

            // Renders the text at the bottom of the box.
            canvas.drawText(text.getText(), rect.left, rect.bottom, textPaint);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.firebasemlkitanddatabinding_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_firebasemlkitanddatabinding2:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity2.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding3:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity3.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding4:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity4.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding5:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity5.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding6:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity6.class));
                return true;


            case R.id.action_firebasemlkitanddatabinding11:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity11.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding12:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity12.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding13:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity13.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding14:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity14.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding15:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity15.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding16:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity16.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding17:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity17.class));
                return true;

            case R.id.action_firebasemlkitanddatabinding18:
                startActivity(new Intent(FirebaseMlKitAndDataBindingActivity1.this, FirebaseMlKitAndDataBindingActivity18.class));
                return true;
        }

        return true;
    }
}
