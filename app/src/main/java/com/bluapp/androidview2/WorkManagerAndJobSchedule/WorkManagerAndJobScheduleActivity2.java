package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity2 extends AppCompatActivity {
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule2);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkManager2.class, 16, TimeUnit.MINUTES)
                .setConstraints(constraints).build();
        WorkManager.getInstance(this).enqueue(periodicWorkRequest);

    }
}
