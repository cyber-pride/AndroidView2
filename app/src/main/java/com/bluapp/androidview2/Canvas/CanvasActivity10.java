package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bluapp.androidview2.R;

public class CanvasActivity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas10);
    }

    public static class canvas10 extends View {
        private Paint paint;
        public canvas10(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(30);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            canvas.drawText("Hello World", 50, 65, paint);

        }
    }
}
