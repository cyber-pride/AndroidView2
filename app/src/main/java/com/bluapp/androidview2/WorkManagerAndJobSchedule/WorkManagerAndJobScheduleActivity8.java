package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;

import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity8 extends AppCompatActivity {
    private TextView tvdata;
    private Button schedule;
    private OneTimeWorkRequest oneTimeWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule8);
        tvdata = (TextView) findViewById(R.id.tvdata);
        schedule = (Button) findViewById(R.id.schedule);
        oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager8.class)
                .setInputData(createInputData("Inducesmile","Hello world, work completed on time"))
                .setInitialDelay(10, TimeUnit.SECONDS).build();

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if(workInfo != null &&  workInfo.getState().isFinished()){
                            tvdata.setText(workInfo.getOutputData().getString("data"));
                        }
                    }
                });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }

    private Data createInputData(String title, String message){
        Data data = new Data.Builder()
                .putString("title", title)
                .putString("message", message)
                .build();
        return data;
    }
}
