package com.bluapp.androidview2.MapAndService;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Service16 extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //if device is not running use
        //Intent intent1 = new Intent(this, MapAndServiceActivity16.class);
       // intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       // startActivity(intent1);


        Intent intent1 = new Intent(this,MapAndServiceActivity16.class);
        startActivity(intent1);



        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
    }
}