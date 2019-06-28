package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity5 extends AppCompatActivity {
    private Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule5);
        schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        if(!isWorkSchedule("Work5")){
            PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkManager5.class, 16, TimeUnit.MINUTES).addTag("Work5").build();;
            WorkManager.getInstance(this).enqueue(periodicWorkRequest);
        }else{
            Toast.makeText(WorkManagerAndJobScheduleActivity5.this, "Work Already Schedule", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isWorkSchedule(String tag){
        WorkManager instance = WorkManager.getInstance(this);
        ListenableFuture<List<WorkInfo>> statues = instance.getWorkInfosByTag(tag);
        try{
            boolean running = false;
            List<WorkInfo> workInfoList = statues.get();
            for(WorkInfo workInfo : workInfoList){
                WorkInfo.State state = workInfo.getState();
                running = state == WorkInfo.State.RUNNING | state == WorkInfo.State.ENQUEUED;
            }
            return running;
        }catch (ExecutionException e){
            e.printStackTrace();
            return false;
        }catch (InterruptedException e){
            e.printStackTrace();
            return false;
        }
    }
}
