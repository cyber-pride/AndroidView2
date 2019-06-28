package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class WorkManagerAndJobScheduleActivity19 extends AppCompatActivity {
    private TextView tvtime;
    private Button timer;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule19);
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
        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
               tvtime.setText(String.valueOf(counter));
                counter++;
            }
            @Override
            public void onFinish() {
                tvtime.setText("Timer Finish");
            }
        }.start();
    }
}
