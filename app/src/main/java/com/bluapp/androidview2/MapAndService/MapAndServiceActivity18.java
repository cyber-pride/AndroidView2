package com.bluapp.androidview2.MapAndService;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;
import com.google.android.material.textfield.TextInputEditText;

public class MapAndServiceActivity18 extends AppCompatActivity {
    private TextInputEditText time;
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service18);
        time = (TextInputEditText) findViewById(R.id.time);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBroadcast();
            }
        });
    }

    private void startBroadcast(){
        int timeinput = Integer.parseInt(time.getText().toString());
        Intent intent = new Intent(this, BrService4.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(timeinput * 1000), pendingIntent);
    }
}
