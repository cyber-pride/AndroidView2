package com.bluapp.androidview2.Canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bluapp.androidview2.R;

public class CanvasActivity1 extends AppCompatActivity {
    private RelativeLayout view;
    private Button clearbtn;
    private canvas1 customcanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas1);
        view = (RelativeLayout) findViewById(R.id.canvas);
        clearbtn = (Button) findViewById(R.id.clear);
        customcanvas = new canvas1(CanvasActivity1.this, null);
        view.addView(customcanvas);
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customcanvas.clearCanvas();
            }
        });
    }

    public static class canvas1 extends View {
        private Paint paint;
        private Canvas mcanvas;
        private boolean cc = false;
        public canvas1(Context context, AttributeSet attributeSet){
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
                mcanvas.drawColor(Color.WHITE);
            }else{
                mcanvas.drawRect(50,50,400,250, paint);
                cc = false;
            }
        }

        public void clearCanvas(){
            cc = true;
            invalidate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.canvas_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_canvasactivity2:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity2.class));
                return true;

            case R.id.action_canvasactivity3:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity3.class));
                return true;

            case R.id.action_canvasactivity4:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity4.class));
                return true;

            case R.id.action_canvasactivity5:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity5.class));
                return true;

            case R.id.action_canvasactivity6:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity6.class));
                return true;

            case R.id.action_canvasactivity7:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity7.class));
                return true;

            case R.id.action_canvasactivity8:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity8.class));
                return true;

            case R.id.action_canvasactivity9:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity9.class));
                return true;

            case R.id.action_canvasactivity10:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity10.class));
                return true;

            case R.id.action_canvasactivity11:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity11.class));
                return true;

            case R.id.action_canvasactivity12:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity12.class));
                return true;

            case R.id.action_canvasactivity13:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity13.class));
                return true;

            case R.id.action_canvasactivity14:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity14.class));
                return true;

            case R.id.action_canvasactivity15:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity15.class));
                return true;

            case R.id.action_canvasactivity16:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity16.class));
                return true;

            case R.id.action_canvasactivity17:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity17.class));
                return true;

            case R.id.action_canvasactivity18:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity18.class));
                return true;

            case R.id.action_canvasactivity19:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity19.class));
                return true;

            case R.id.action_canvasactivity20:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity20.class));
                return true;

            case R.id.action_canvasactivity21:
                startActivity(new Intent(CanvasActivity1.this, CanvasActivity21.class));
                return true;
        }

        return true;
    }
}
