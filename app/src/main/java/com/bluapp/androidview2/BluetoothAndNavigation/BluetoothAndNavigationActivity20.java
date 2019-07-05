package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;

import com.bluapp.androidview2.R;

public class BluetoothAndNavigationActivity20 extends AppCompatActivity {
    private Viewmodel20 viewmodel20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation20);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                viewmodel20 = ViewModelProviders.of(BluetoothAndNavigationActivity20.this).get(Viewmodel20.class);;
                viewmodel20.updateName("Inducesmile");
            }
        }, 5000);

    }
}
