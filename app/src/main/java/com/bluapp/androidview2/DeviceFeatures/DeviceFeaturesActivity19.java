package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity19 extends AppCompatActivity {
    private Button playRingtone;
    private Button stopRingtone;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features19);
        playRingtone = (Button) findViewById(R.id.playRingtone);
        stopRingtone = (Button) findViewById(R.id.stopRingtone);
        try {
        mediaPlayer = MediaPlayer.create(DeviceFeaturesActivity19.this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.prepare();
        mediaPlayer.setVolume(1f, 1f);
        mediaPlayer.setLooping(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        playRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        stopRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });

    }
}
