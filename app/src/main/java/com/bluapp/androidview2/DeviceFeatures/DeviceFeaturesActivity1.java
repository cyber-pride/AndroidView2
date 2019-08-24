package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity1 extends AppCompatActivity {
    private Button deviceInfoBtn;
    private TextView infoDisplayTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features1);
        deviceInfoBtn = (Button) findViewById(R.id.deviceInfoBtn);
        infoDisplayTxt = (TextView) findViewById(R.id.infoDisplayTxt);

        deviceInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceInfo();
            }
        });
    }

    private void getDeviceInfo(){
        infoDisplayTxt.setText("SERIAL: " + Build.SERIAL + "\n" +
                "MODEL: " + Build.MODEL + "\n" +
                "ID: " + Build.ID + "\n" +
                "Manufacture: " + Build.MANUFACTURER + "\n" +
                "Brand: " + Build.BRAND + "\n" +
                "Type: " + Build.TYPE + "\n" +
                "User: " + Build.USER + "\n" +
                "BASE: " + Build.VERSION_CODES.BASE + "\n" +
                "INCREMENTAL: " + Build.VERSION.INCREMENTAL + "\n" +
                "SDK:  " + String.valueOf(Build.VERSION.SDK_INT) + "\n" +
                "BOARD: " + Build.BOARD + "\n" +
                "BRAND: " + Build.BRAND + "\n" +
                "HOST: " + Build.HOST + "\n" +
                "FINGERPRINT: "+Build.FINGERPRINT + "\n" +
                "Version Code: " + Build.VERSION.RELEASE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.devicefeatures_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_devicefeatures2:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity2.class));
                return true;

            case R.id.action_devicefeatures3:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity3.class));
                return true;

            case R.id.action_devicefeatures4:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity4.class));
                return true;

            case R.id.action_devicefeatures5:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity5.class));
                return true;

            case R.id.action_devicefeatures6:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity6.class));
                return true;

            case R.id.action_devicefeatures7:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity7.class));
                return true;

            case R.id.action_devicefeatures8:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity8.class));
                return true;

            case R.id.action_devicefeatures9:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity9.class));
                return true;

            case R.id.action_devicefeatures10:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity10.class));
                return true;

            case R.id.action_devicefeatures11:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity11.class));
                return true;

            case R.id.action_devicefeatures12:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity12.class));
                return true;

            case R.id.action_devicefeatures13:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity13.class));
                return true;

            case R.id.action_devicefeatures14:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity14.class));
                return true;

            case R.id.action_devicefeatures15:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity15.class));
                return true;

            case R.id.action_devicefeatures16:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity16.class));
                return true;

            case R.id.action_devicefeatures17:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity17.class));
                return true;

            case R.id.action_devicefeatures18:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity18.class));
                return true;

            case R.id.action_devicefeatures19:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity19.class));
                return true;

            case R.id.action_devicefeatures20:
                startActivity(new Intent(DeviceFeaturesActivity1.this, DeviceFeaturesActivity20.class));
                return true;



        }

        return true;
    }
}
