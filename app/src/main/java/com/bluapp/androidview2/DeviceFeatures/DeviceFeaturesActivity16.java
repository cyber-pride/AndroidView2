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

public class DeviceFeaturesActivity16 extends AppCompatActivity {
    private TextView shakeDevice;
    private SensorManager sensorManager;
    private ShakeEventListener shakeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features16);
        shakeDevice = (TextView) findViewById(R.id.shakeDevice);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        shakeEventListener = new ShakeEventListener();
        shakeEventListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
            public void onShake() {
                shakeDevice.setText("Device Was Shake");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(shakeEventListener);
        super.onPause();
    }

    public static class ShakeEventListener implements SensorEventListener {
        private static final int MIN_FORCE = 10;
        private static final int MIN_DIRECTION_CHANGE = 3;
        private static final int MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE = 200;
        private static final int MAX_TOTAL_DURATION_OF_SHAKE = 400;
        private long mFirstDirectionChangeTime = 0;
        private long mLastDirectionChangeTime;

        private int mDirectionChangeCount = 0;
        private float lastX = 0;
        private float lastY = 0;
        private float lastZ = 0;
        private OnShakeListener mShakeListener;

        public interface OnShakeListener {
            void onShake();
        }

        public void setOnShakeListener(OnShakeListener listener) {
            mShakeListener = listener;
        }

        @Override
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[SensorManager.DATA_X];
            float y = se.values[SensorManager.DATA_Y];
            float z = se.values[SensorManager.DATA_Z];
            float totalMovement = Math.abs(x + y + z - lastX - lastY - lastZ);
            if (totalMovement > MIN_FORCE) {
                long now = System.currentTimeMillis();
                if (mFirstDirectionChangeTime == 0) {
                    mFirstDirectionChangeTime = now;
                    mLastDirectionChangeTime = now;
                }
                long lastChangeWasAgo = now - mLastDirectionChangeTime;
                if (lastChangeWasAgo < MAX_PAUSE_BETHWEEN_DIRECTION_CHANGE) {
                    mLastDirectionChangeTime = now;
                    mDirectionChangeCount++;
                    lastX = x;
                    lastY = y;
                    lastZ = z;
                    if (mDirectionChangeCount >= MIN_DIRECTION_CHANGE) {
                        long totalDuration = now - mFirstDirectionChangeTime;
                        if (totalDuration < MAX_TOTAL_DURATION_OF_SHAKE) {
                            mShakeListener.onShake();
                            resetShakeParameters();
                        }
                    }
                } else {
                    resetShakeParameters();
                }
            }
        }
        private void resetShakeParameters() {
            mFirstDirectionChangeTime = 0;
            mDirectionChangeCount = 0;
            mLastDirectionChangeTime = 0;
            lastX = 0;
            lastY = 0;
            lastZ = 0;
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }
}
