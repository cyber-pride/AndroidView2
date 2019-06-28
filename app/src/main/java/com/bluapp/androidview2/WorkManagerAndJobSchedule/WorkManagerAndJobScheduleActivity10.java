package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity10 extends AppCompatActivity {
    private Button schedule;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule10);
        schedule = (Button) findViewById(R.id.schedule);
        cancel = (Button) findViewById(R.id.cancel);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelWork();
            }
        });
    }

    private void startWork(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager10.class)
                .addTag("Work10").setInitialDelay(10, TimeUnit.SECONDS).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
        Toast.makeText(WorkManagerAndJobScheduleActivity10.this, "Work Schedule", Toast.LENGTH_LONG).show();
    }

    private void cancelWork(){
        WorkManager.getInstance(this).cancelAllWorkByTag("Work10");
        Toast.makeText(WorkManagerAndJobScheduleActivity10.this, "Work Cancel", Toast.LENGTH_LONG).show();
    }
}
