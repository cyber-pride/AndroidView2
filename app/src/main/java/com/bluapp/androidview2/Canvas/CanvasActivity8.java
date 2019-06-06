package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas8);
    }

    public static class canvas8 extends View {
        private Paint paint;
        public canvas8(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            canvas.drawLine(50,100,200,300, paint);

        }
    }
}
