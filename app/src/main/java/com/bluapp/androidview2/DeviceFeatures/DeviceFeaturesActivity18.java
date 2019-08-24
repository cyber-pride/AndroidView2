package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity18 extends AppCompatActivity {
    private Button vibrate;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features18);
        vibrate = (Button) findViewById(R.id.vibrate);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(600, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(600);
                }
            }
        });
    }
}
