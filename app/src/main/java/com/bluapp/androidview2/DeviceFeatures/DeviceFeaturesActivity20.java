package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity20 extends AppCompatActivity {
    private Button playSound;
    private Button stopSound;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features20);
        playSound = (Button) findViewById(R.id.playSound);
        stopSound = (Button) findViewById(R.id.stopSound);
        mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("music.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        stopSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
    }
}
