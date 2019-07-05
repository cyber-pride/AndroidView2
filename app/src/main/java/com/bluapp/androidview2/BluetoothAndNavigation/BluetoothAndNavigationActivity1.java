package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluapp.androidview2.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class BluetoothAndNavigationActivity1 extends AppCompatActivity {
    private TextView status;
    private TextView printerName;
    private EditText message;
    private Button printbtn;
    private Button connectbtn;
    private Button disconnectbtn;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private BluetoothDevice bluetoothDevice;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Thread thread;
    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation1);
        printerName = (TextView)findViewById(R.id.printerName);
        status = (TextView)findViewById(R.id.status);
        message = (EditText)findViewById(R.id.messageEdt);
        printbtn = (Button)findViewById(R.id.printBtn);
        connectbtn = (Button)findViewById(R.id.connectBtn);
        disconnectbtn = (Button)findViewById(R.id.disconnectBtn);

        connectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FindBluetoothDevice();
                    openBluetoothPrinter();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        disconnectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    disconnect();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        printbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    printData();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }


    private void FindBluetoothDevice(){
        try{
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter==null){
                printerName.setText("No Bluetooth Adapter found");
            }
            if(!bluetoothAdapter.isEnabled()){
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT,0);
            }
            Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
            if(pairedDevice.size()>0){
                for(BluetoothDevice pairedDev:pairedDevice){
                    // RPP300 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    if(pairedDev.getName().equals("RPP300")){
                        bluetoothDevice=pairedDev;
                        printerName.setText("Bluetooth Printer Attached: "+pairedDev.getName());
                        status.setText("Bluetooth Printer Attached: "+pairedDev.getName());
                        break;
                    }
                }
            }
            status.setText("Bluetooth Printer Attached");
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    private void openBluetoothPrinter() throws IOException{
        try{
            UUID uuidSting = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            bluetoothSocket=bluetoothDevice.createRfcommSocketToServiceRecord(uuidSting);
            bluetoothSocket.connect();
            outputStream=bluetoothSocket.getOutputStream();
            inputStream=bluetoothSocket.getInputStream();
            beginListenData();
        }catch (Exception ex){

        }
    }

    private void beginListenData(){
        try{
            final Handler handler =new Handler();
            final byte delimiter=10;
            stopWorker =false;
            readBufferPosition=0;
            readBuffer = new byte[1024];
            thread=new Thread(new Runnable() {
                @Override
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker){
                        try{
                            int byteAvailable = inputStream.available();
                            if(byteAvailable>0){
                                byte[] packetByte = new byte[byteAvailable];
                                inputStream.read(packetByte);

                                for(int i=0; i<byteAvailable; i++){
                                    byte b = packetByte[i];
                                    if(b==delimiter){
                                        byte[] encodedByte = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer,0,
                                                encodedByte,0,
                                                encodedByte.length
                                        );
                                        final String data = new String(encodedByte,"US-ASCII");
                                        readBufferPosition=0;
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                printerName.setText(data);
                                            }
                                        });
                                    }else{
                                        readBuffer[readBufferPosition++]=b;
                                    }
                                }
                            }
                        }catch(Exception ex){
                            stopWorker=true;
                        }
                    }

                }
            });

            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void printData() throws  IOException{
        try{
            String msg = message.getText().toString();
            msg+="\n";
            outputStream.write(msg.getBytes());
            status.setText("Printing Text...");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void disconnect() throws IOException{
        try {
            stopWorker=true;
            outputStream.close();
            inputStream.close();
            bluetoothSocket.close();
            status.setText("Printer Disconnected.");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bluetoothandnavigation_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_bluetoothandnavigation2:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity2.class));
                return true;

            case R.id.action_bluetoothandnavigation3:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity3.class));
                return true;

            case R.id.action_bluetoothandnavigation4:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity4.class));
                return true;

            case R.id.action_bluetoothandnavigation5:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity5.class));
                return true;

            case R.id.action_bluetoothandnavigation6:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity6.class));
                return true;


            case R.id.action_bluetoothandnavigation8:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity8.class));
                return true;

            case R.id.action_bluetoothandnavigation9:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity9.class));
                return true;

            case R.id.action_bluetoothandnavigation10:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity10.class));
                return true;

            case R.id.action_bluetoothandnavigation11:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity11.class));
                return true;

            case R.id.action_bluetoothandnavigation12:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity12.class));
                return true;

            case R.id.action_bluetoothandnavigation13:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity13.class));
                return true;

            case R.id.action_bluetoothandnavigation14:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity14.class));
                return true;

            case R.id.action_bluetoothandnavigation15:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity15.class));
                return true;

            case R.id.action_bluetoothandnavigation16:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity16.class));
                return true;

            case R.id.action_bluetoothandnavigation17:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity17.class));
                return true;

            case R.id.action_bluetoothandnavigation18:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity18.class));
                return true;

            case R.id.action_bluetoothandnavigation19:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity19.class));
                return true;

            case R.id.action_bluetoothandnavigation20:
                startActivity(new Intent(BluetoothAndNavigationActivity1.this, BluetoothAndNavigationActivity20.class));
                return true;


        }

        return true;
    }


}
