package com.bluapp.androidview2.MapAndService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Service14 extends IntentService {
    public static final int DOWNLOAD_SUCCESS=11;

    public Service14(){
        super(Service14.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String path = intent.getStringExtra("url");
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        Bundle bundle=new Bundle();
        try{
            File outputFile = new File(Environment.getExternalStorageDirectory(), "inducesmile.png");
            URL url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            FileOutputStream fos = new FileOutputStream(outputFile);
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = inputStream.read(buffer))>0){
                fos.write(buffer, 0, len1);
            }
            fos.close();
            inputStream.close();
            String file_path = outputFile.getPath();
            bundle.putString("file_path",file_path);
            receiver.send(DOWNLOAD_SUCCESS,bundle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}