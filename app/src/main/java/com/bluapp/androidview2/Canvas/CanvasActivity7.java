package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.bluapp.androidview2.R;

public class CanvasActivity7 extends AppCompatActivity {
    private ImageView img;
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas7);
        img = (ImageView) findViewById(R.id.image);
        offset = 50;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        Bitmap dstBitmap = Bitmap.createBitmap(bitmap.getWidth() + offset * 2, bitmap.getHeight() + offset * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dstBitmap);
        canvas.drawColor(Color.LTGRAY);
        canvas.drawBitmap(bitmap, offset, offset, null);
        img.setImageBitmap(dstBitmap);
    }
}
