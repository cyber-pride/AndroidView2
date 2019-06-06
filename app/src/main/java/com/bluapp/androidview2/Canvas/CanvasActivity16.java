package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bluapp.androidview2.R;

public class CanvasActivity16 extends AppCompatActivity {
    private RelativeLayout view;
    private Button scale;
    private canvas16 customcanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas16);
        view = (RelativeLayout) findViewById(R.id.canvas);
        scale = (Button) findViewById(R.id.scale);
        customcanvas = new canvas16(CanvasActivity16.this, null);
        view.addView(customcanvas);
        scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customcanvas.scaleCanvas();
            }
        });
    }
    public static class canvas16 extends View {
        private Paint paint;
        private boolean cc = false;
        private Canvas mcanvas;
        public canvas16(Context context, AttributeSet attributeSet){
            super(context, attributeSet);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            mcanvas = canvas;
            if(cc){
                mcanvas.save();
                mcanvas.scale(0.5f,0.5f);
                mcanvas.drawRect(50, 50, 200, 200, paint);
                mcanvas.restore();
            }else{
                mcanvas.drawRect(50, 50, 200, 200, paint);
                cc = false;
            }
        }

        public void scaleCanvas(){
            cc = true;
            invalidate();
        }
    }
}
