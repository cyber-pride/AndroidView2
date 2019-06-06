package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity19 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas19);
    }
    public static class canvas19 extends View {
        private float x = 50;
        private float y = 60;
        private Paint paint;

        public canvas19(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(30);
            canvas.drawText("Hello World", x, y, paint);
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
                  //  x = (int)event.getX();
                   // y = (int)event.getY();
                    break;
            }
            return true;
        }
    }
}
