package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity21 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas21);
    }

    public static class canvas21 extends View {
        private Paint paint;
        private Path path;
        public canvas21(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            path = new Path();
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStrokeWidth(3);
            path.moveTo(50,50);
            path.cubicTo(300, 50, 100, 400, 400, 400);
            canvas.drawPath(path, paint);

            path.reset();
            paint.setColor(getResources().getColor(R.color.colorPrimary));
            paint.setStrokeWidth(1);
            path.moveTo(50,50);
            path.lineTo(300,50);
            path.lineTo(100,400);
            path.lineTo(400,400);
            canvas.drawPath(path, paint);

        }

    }
}
