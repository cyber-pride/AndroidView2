package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluapp.androidview2.R;

import java.util.ArrayList;

public class BluetoothAndNavigationActivity6 extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "AndroidView2";
    private BluetoothAdapter mBluetoothAdapter;
    private Button btnONOFF;
    private Button discoverableBtn;
    private Button findUnpairedBtn;
    private ListView lvNewDevices;
    private ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
    private DeviceListAdapter mDeviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation10);
        btnONOFF = (Button) findViewById(R.id.btnONOFF);
        discoverableBtn = (Button) findViewById(R.id.discoverableBtn);
        findUnpairedBtn = (Button) findViewById(R.id.findUnpairedBtn);
        lvNewDevices = (ListView) findViewById(R.id.list);
        mBTDevices = new ArrayList<>();

        //Broadcasts when bond state changes (ie:pairing)
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mBroadcastReceiver4, filter);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        lvNewDevices.setOnItemClickListener(BluetoothAndNavigationActivity6.this);

        btnONOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: enabling/disabling bluetooth.");
                Toast.makeText(BluetoothAndNavigationActivity6.this, "onClick: enabling/disabling bluetooth.", Toast.LENGTH_LONG).show();
                enableDisableBT();
            }
        });

        discoverableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableDisableDiscoverable();
            }
        });

        findUnpairedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discover();
            }
        });

    }

    public void enableDisableDiscoverable() {
        Log.d(TAG, "btnEnableDisable_Discoverable: Making device discoverable for 300 seconds.");
        Toast.makeText(BluetoothAndNavigationActivity6.this, "btnEnableDisable_Discoverable: Making device discoverable for 300 seconds.", Toast.LENGTH_LONG).show();
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
        IntentFilter intentFilter = new IntentFilter(mBluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        registerReceiver(mBroadcastReceiver2,intentFilter);

    }

    public void Discover() {
        Log.d(TAG, "btnDiscover: Looking for unpaired devices.");
        Toast.makeText(BluetoothAndNavigationActivity6.this, "btnDiscover: Looking for unpaired devices.", Toast.LENGTH_LONG).show();
        if(mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.cancelDiscovery();
            Log.d(TAG, "btnDiscover: Canceling discovery.");
            Toast.makeText(BluetoothAndNavigationActivity6.this, "btnDiscover: Canceling discovery.", Toast.LENGTH_LONG).show();
            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
        if(!mBluetoothAdapter.isDiscovering()){
            mBluetoothAdapter.startDiscovery();
            IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mBroadcastReceiver3, discoverDevicesIntent);
        }
    }


    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mBroadcastReceiver1 = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);
                switch(state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "onReceive: STATE OFF");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "onReceive: STATE OFF", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING OFF");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver1: STATE TURNING OFF", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE ON");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver1: STATE ON", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "mBroadcastReceiver1: STATE TURNING ON");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver1: STATE TURNING ON", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    /**
     * Broadcast Receiver for changes made to bluetooth states such as:
     * 1) Discoverability mode on/off or expire.
     */
    private final BroadcastReceiver mBroadcastReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED)) {
                int mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.ERROR);
                switch (mode) {
                    //Device is in Discoverable Mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Enabled.");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver2: Discoverability Enabled.", Toast.LENGTH_LONG).show();
                        break;
                    //Device not in discoverable mode
                    case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Disabled. Able to receive connections.");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver2: Discoverability Disabled. Able to receive connections.", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.SCAN_MODE_NONE:
                        Log.d(TAG, "mBroadcastReceiver2: Discoverability Disabled. Not able to receive connections.");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver2: Discoverability Disabled. Not able to receive connections.", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.STATE_CONNECTING:
                        Log.d(TAG, "mBroadcastReceiver2: Connecting....");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver2: Connecting....", Toast.LENGTH_LONG).show();
                        break;
                    case BluetoothAdapter.STATE_CONNECTED:
                        Log.d(TAG, "mBroadcastReceiver2: Connected.");
                        Toast.makeText(BluetoothAndNavigationActivity6.this, "mBroadcastReceiver2: Connected.", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        }
    };


    /**
     * Broadcast Receiver for listing devices that are not yet paired
     * -Executed by btnDiscover() method.
     */
    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");
            Toast.makeText(BluetoothAndNavigationActivity6.this, "onReceive: ACTION FOUND.", Toast.LENGTH_LONG).show();
            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                Toast.makeText(BluetoothAndNavigationActivity6.this, "onReceive: " + device.getName() + ": " + device.getAddress(), Toast.LENGTH_LONG).show();
                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.device_adapter_layout, mBTDevices);
                lvNewDevices.setAdapter(mDeviceListAdapter);
            }
        }
    };

    /**
     * Broadcast Receiver that detects bond state changes (Pairing status changes)
     */
    private final BroadcastReceiver mBroadcastReceiver4 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if(action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)){
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED){
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                    Toast.makeText(BluetoothAndNavigationActivity6.this, "BroadcastReceiver: BOND_BONDED.", Toast.LENGTH_LONG).show();
                }
                //case2: creating a bone
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                    Toast.makeText(BluetoothAndNavigationActivity6.this, "BroadcastReceiver: BOND_BONDING.", Toast.LENGTH_LONG).show();
                }
                //case3: breaking a bond
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                    Toast.makeText(BluetoothAndNavigationActivity6.this, "BroadcastReceiver: BOND_NONE.", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called.");
        Toast.makeText(BluetoothAndNavigationActivity6.this, "onDestroy: called.", Toast.LENGTH_LONG).show();
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver1);
        unregisterReceiver(mBroadcastReceiver2);
        unregisterReceiver(mBroadcastReceiver3);
        unregisterReceiver(mBroadcastReceiver4);
    }

    public void enableDisableBT(){
        if(mBluetoothAdapter == null){
            Log.d(TAG, "enableDisableBT: Does not have BT capabilities.");
            Toast.makeText(BluetoothAndNavigationActivity6.this, "enableDisableBT: Does not have BT capabilities.", Toast.LENGTH_LONG).show();
        }
        if(!mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "enableDisableBT: enabling BT.");
            Toast.makeText(BluetoothAndNavigationActivity6.this, "enableDisableBT: enabling BT.", Toast.LENGTH_LONG).show();
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
        if(mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "enableDisableBT: disabling BT.");
            Toast.makeText(BluetoothAndNavigationActivity6.this, "enableDisableBT: disabling BT.", Toast.LENGTH_LONG).show();
            mBluetoothAdapter.disable();
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadcastReceiver1, BTIntent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //first cancel discovery because its very memory intensive.
        mBluetoothAdapter.cancelDiscovery();
        Log.d(TAG, "onItemClick: You Clicked on a device.");
        Toast.makeText(BluetoothAndNavigationActivity6.this, "onItemClick: You Clicked on a device.", Toast.LENGTH_LONG).show();
        String deviceName = mBTDevices.get(i).getName();
        String deviceAddress = mBTDevices.get(i).getAddress();
        Log.d(TAG, "onItemClick: deviceName = " + deviceName);
        Toast.makeText(BluetoothAndNavigationActivity6.this, "onItemClick: deviceName = " + deviceName, Toast.LENGTH_LONG).show();
        Log.d(TAG, "onItemClick: deviceAddress = " + deviceAddress);
        Toast.makeText(BluetoothAndNavigationActivity6.this, "onItemClick: deviceAddress = " + deviceAddress, Toast.LENGTH_LONG).show();
        //create the bond.
        //NOTE: Requires API 17+? I think this is JellyBean
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
            Log.d(TAG, "Trying to pair with " + deviceName);
            Toast.makeText(BluetoothAndNavigationActivity6.this, "Trying to pair with " + deviceName, Toast.LENGTH_LONG).show();
            mBTDevices.get(i).createBond();
        }
    }

    public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {
        private LayoutInflater mLayoutInflater;
        private ArrayList<BluetoothDevice> mDevices;
        private int  mViewResourceId;

        public DeviceListAdapter(Context context, int tvResourceId, ArrayList<BluetoothDevice> devices){
            super(context, tvResourceId,devices);
            this.mDevices = devices;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mViewResourceId = tvResourceId;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mLayoutInflater.inflate(mViewResourceId, null);
            BluetoothDevice device = mDevices.get(position);
            if (device != null) {
                TextView deviceName = (TextView) convertView.findViewById(R.id.tvDeviceName);
                TextView deviceAdress = (TextView) convertView.findViewById(R.id.tvDeviceAddress);
                if (deviceName != null) {
                    deviceName.setText(device.getName());
                }
                if (deviceAdress != null) {
                    deviceAdress.setText(device.getAddress());
                }
            }

            return convertView;
        }

    }
}