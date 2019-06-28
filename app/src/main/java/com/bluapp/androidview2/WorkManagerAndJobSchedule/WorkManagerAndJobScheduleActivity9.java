package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity9 extends AppCompatActivity {
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule9);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork() {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager9.class)
                .setInitialDelay(2, TimeUnit.SECONDS).build();

        OneTimeWorkRequest secondoneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager91.class)
                .setInitialDelay(3, TimeUnit.SECONDS).build();

        WorkContinuation workContinuation = WorkManager.getInstance(this).beginWith(oneTimeWorkRequest);
        workContinuation.then(secondoneTimeWorkRequest)
                .enqueue();
    }
}
