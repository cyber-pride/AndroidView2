package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.bluapp.androidview2.R;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

public class CanvasActivity6 extends AppCompatActivity {
    private ImageView img;
    private SVG svg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas6);
        img = (ImageView) findViewById(R.id.image);
        try {
            svg = SVG.getFromResource(getResources(), R.raw.androidlogo);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }
        if(svg.getDocumentWidth() != - 1F) {
            svg.setDocumentHeight(600f);
            svg.setDocumentWidth(600f);
            Bitmap bitmap = Bitmap.createBitmap(700, 700, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(0, 255, 255, 255);
            svg.renderToCanvas(canvas);
            img.setBackground(new BitmapDrawable(getResources(), bitmap));
        }
    }
}
