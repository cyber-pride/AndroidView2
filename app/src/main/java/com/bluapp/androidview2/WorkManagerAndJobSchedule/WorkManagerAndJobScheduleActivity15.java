package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class WorkManagerAndJobScheduleActivity15 extends AppCompatActivity {
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule15);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startJob();
            }
        });
    }

    private void startJob(){
        ComponentName componentName = new ComponentName(this, JobScheduler15.class);
        JobInfo jobInfo = new JobInfo.Builder(13, componentName)
                .setRequiresCharging(true)
                .setMinimumLatency(1)
                .setOverrideDeadline(1)
                .build();
        JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Toast.makeText(WorkManagerAndJobScheduleActivity15.this, "Job Schedule",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(WorkManagerAndJobScheduleActivity15.this, "Job Not Schedule",Toast.LENGTH_LONG).show();
        }

    }

}
