package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class CanvasActivity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas13);
    }

    public static class canvas13 extends View {
        private Paint paint;
        private Rect bounds;
        public canvas13(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            paint = new Paint();
            bounds = new Rect();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStyle(Paint.Style.FILL);
            paint.setTypeface(Typeface.DEFAULT);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(30);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            String text = "Hello World";
            paint.getTextBounds(text, 0, text.length(), bounds);
            int x = (canvas.getWidth()/2);
            int y = (int) ((canvas.getHeight()/2));
            int text_height = bounds.height();
            int text_width = bounds.width();
            canvas.drawText(text, x, y, paint);
            Toast.makeText(getContext(),"Height: "+String.valueOf(text_height)+" Width: "+String.valueOf(text_width),Toast.LENGTH_LONG).show();
        }
    }
}
