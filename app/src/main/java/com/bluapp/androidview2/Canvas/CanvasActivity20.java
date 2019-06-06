package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity20 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas20);
    }

    public static class canvas20 extends View {
        private Bitmap bitmap;
        private float x, y;
        private Paint paint;

        public canvas20(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_user);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.CYAN);
            canvas.drawBitmap(bitmap, x, y, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;

                case MotionEvent.ACTION_MOVE:
                    x = (int)event.getX();
                    y = (int)event.getY();
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    x = (int)event.getX();
                    y = (int)event.getY();
                    break;
            }
            return true;
        }
    }
}
