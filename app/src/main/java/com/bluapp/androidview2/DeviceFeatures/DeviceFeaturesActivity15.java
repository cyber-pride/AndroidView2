package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity15 extends AppCompatActivity {
    private TextView proximityTxt;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features15);
        proximityTxt = (TextView) findViewById(R.id.proximityTxt);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (sensor == null) {
            proximityTxt.setText("No Proximity Sensor!");
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
            if (event.sensor.getType() < sensor.getMaximumRange()) {
                if (event.values[0] == 0) {
                    proximityTxt.setText("Proximity Is Close");
                } else {
                    proximityTxt.setText("Proximity Is Far");
                }
            }
        }
    };
}
