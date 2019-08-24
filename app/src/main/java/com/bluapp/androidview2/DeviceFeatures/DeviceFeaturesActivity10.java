package com.bluapp.androidview2.DeviceFeatures;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity10 extends AppCompatActivity {
    private TextView hexTxt;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features10);
        hexTxt = (TextView) findViewById(R.id.hexTxt);
        hexTxt.setTextColor(getColor(R.color.colorAccent));
    }
}
