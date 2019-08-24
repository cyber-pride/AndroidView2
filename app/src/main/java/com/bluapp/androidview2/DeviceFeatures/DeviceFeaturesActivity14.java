package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity14 extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView gyroscopeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features14);
        gyroscopeTxt = (TextView) findViewById(R.id.gyroscopeTxt);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (sensor == null) {
            gyroscopeTxt.setText("No Gyroscope Sensor!");
        } else {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if(event.values[2] > 0.5f) {
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            } else if(event.values[2] < -0.5f) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        }
    };
}
