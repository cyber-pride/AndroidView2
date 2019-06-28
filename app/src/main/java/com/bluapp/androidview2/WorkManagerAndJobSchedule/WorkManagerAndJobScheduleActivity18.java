package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;

import java.util.Timer;
import java.util.TimerTask;

public class WorkManagerAndJobScheduleActivity18 extends AppCompatActivity {
    private TextView tvtime;
    private Button timer;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule18);
        tvtime = (TextView) findViewById(R.id.tvtime);
        timer = (Button) findViewById(R.id.timer);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
    }

    private void startTimer(){
        final Timer mtimer = new Timer();
        mtimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvtime.setText(String.valueOf(time));
                        time++;
                    }
                });
            }
        }, 0, 1000);

    }
}
