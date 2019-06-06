package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas9);
    }

    public static class canvas9 extends View {
        private Paint paint;
        public canvas9(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);

        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            Path path = new Path();
            int x1 = 10;
            int y1 = 50;
            int x2 = 300;
            int y2 = 50;
            int curveRadius = 50;
            int midX = x1+((x2-x1)/2);
            int midY = y1+((y2-y1)/2);
            float xDiff = midX - x1;
            float yDiff = midY - y1;
            double angle = (Math.atan2(yDiff, xDiff) * (180/Math.PI))-90;
            double angleRadians = Math.toRadians(angle);
            float pointX = (float) (midX + curveRadius * Math.cos(angleRadians));
            float pointY = (float) (midY + curveRadius * Math.sin(angleRadians));
            path.moveTo(x1, y1);
            path.cubicTo(x1, y1, pointX, pointY, x2, y2);
            canvas.drawPath(path, paint);
        }
}
}
