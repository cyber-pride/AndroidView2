package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity7 extends AppCompatActivity {
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule7);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager7.class).setInitialDelay(10, TimeUnit.SECONDS).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }
}
