package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity14 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas14);
    }

    public static class canvas14 extends View {
        private Paint paint;
        private Path path;

        public canvas14(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            paint = new Paint();
            path = new Path();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(10);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(event.getX(), event.getY());
                    break;

                case MotionEvent.ACTION_MOVE:
                    path.lineTo(event.getX(), event.getY());
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    }
}
