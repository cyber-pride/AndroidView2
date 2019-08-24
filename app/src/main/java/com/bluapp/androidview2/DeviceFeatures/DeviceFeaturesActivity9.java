package com.bluapp.androidview2.DeviceFeatures;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity9 extends AppCompatActivity {
    private Button showToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features9);
        showToast = (Button) findViewById(R.id.showToast);
        showToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customToast();
            }
        });
    }

    private void customToast() {
        View toastView = getLayoutInflater().inflate(R.layout.custom_toastview, null);
        Toast toast = new Toast(DeviceFeaturesActivity9.this);
        toast.setView(toastView);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();

    }
}
