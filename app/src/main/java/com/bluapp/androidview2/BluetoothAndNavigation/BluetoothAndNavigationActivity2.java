package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class BluetoothAndNavigationActivity2 extends AppCompatActivity {
    private Button onBtn;
    private Button offBtn;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation2);
        onBtn = (Button)findViewById(R.id.onBtn);
        offBtn = (Button)findViewById(R.id.offBtn);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null){
                    Toast.makeText(BluetoothAndNavigationActivity2.this, "Bluetooth not supported", Toast.LENGTH_LONG).show();
                }else{
                    if(!bluetoothAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
                        Toast.makeText(BluetoothAndNavigationActivity2.this, "Bluetooth turned on", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter.disable();
                Toast.makeText(BluetoothAndNavigationActivity2.this, "Bluetooth turned off", Toast.LENGTH_LONG).show();
            }
        });
    }
}
