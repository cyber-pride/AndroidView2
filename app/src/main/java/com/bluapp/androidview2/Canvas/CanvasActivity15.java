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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bluapp.androidview2.R;

public class CanvasActivity15 extends AppCompatActivity {
    private RelativeLayout view;
    private Button rotate;
    private canvas15 customcanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas15);
        view = (RelativeLayout) findViewById(R.id.canvas);
        rotate = (Button) findViewById(R.id.rotate);
        customcanvas = new canvas15(CanvasActivity15.this, null);
        view.addView(customcanvas);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customcanvas.rotateCanvas();
            }
        });
    }

    public static class canvas15 extends View {
        private Paint paint;
        private Canvas mcanvas;
        private boolean cc = false;
        public canvas15(Context context, AttributeSet attributeSet){
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
                mcanvas.rotate(25, mcanvas.getWidth()/2, mcanvas.getHeight()/2);
                mcanvas.drawRect(50,50,200,200, paint);
                mcanvas.restore();
            }else{
                mcanvas.drawRect(50,50,200,200, paint);
                cc = false;
            }
        }

        public void rotateCanvas(){
            cc = true;
            invalidate();
        }
    }
}
