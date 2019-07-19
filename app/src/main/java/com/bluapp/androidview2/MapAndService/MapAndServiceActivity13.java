package com.bluapp.androidview2.MapAndService;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class MapAndServiceActivity13 extends AppCompatActivity {
    private Button btnService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service13);
        btnService = (Button) findViewById(R.id.btnSer);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyServiceRunning(Service13.class)) {
                    stopService(new Intent(MapAndServiceActivity13.this, Service13.class));
                    Toast.makeText(MapAndServiceActivity13.this, "Service Stoped", Toast.LENGTH_LONG).show();
                } else {
                    startService(new Intent(MapAndServiceActivity13.this, Service13.class));
                    Toast.makeText(MapAndServiceActivity13.this, "Service Started", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
