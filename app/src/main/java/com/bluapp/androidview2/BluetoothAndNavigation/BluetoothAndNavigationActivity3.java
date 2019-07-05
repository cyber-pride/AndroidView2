package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bluapp.androidview2.R;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothAndNavigationActivity3 extends AppCompatActivity {
    private Button onBtn;
    private Button offBtn;
    private Button listDevice;
    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation3);
        onBtn = (Button)findViewById(R.id.onBtn);
        offBtn = (Button)findViewById(R.id.offBtn);
        listDevice = (Button)findViewById(R.id.listDevice);
        listView = (ListView) findViewById(R.id.listView);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null){
                    Toast.makeText(BluetoothAndNavigationActivity3.this, "Bluetooth not supported", Toast.LENGTH_LONG).show();
                }else{
                    if(!bluetoothAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
                        Toast.makeText(BluetoothAndNavigationActivity3.this, "Bluetooth turned on", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter.disable();
                Toast.makeText(BluetoothAndNavigationActivity3.this, "Bluetooth turned off", Toast.LENGTH_LONG).show();
            }
        });

        listDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                ArrayList list = new ArrayList();
                if(pairedDevices.size()>0){
                    for(BluetoothDevice pairedDev:pairedDevices){
                        list.add(pairedDev.getName());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BluetoothAndNavigationActivity3.this, android.R.layout.simple_list_item_1, list);
                        listView.setAdapter(adapter);
                    }
                }
            }
        });
    }
}
