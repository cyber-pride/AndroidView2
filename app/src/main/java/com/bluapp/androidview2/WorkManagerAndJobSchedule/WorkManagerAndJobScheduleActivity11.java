package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluapp.androidview2.R;
import com.google.firebase.firestore.auth.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkManagerAndJobScheduleActivity11 extends AppCompatActivity {
    private TextView tvdata;
    private Button schedule;
    private DataDatabase dataDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule11);
        tvdata = (TextView) findViewById(R.id.tvdata);
        schedule = (Button) findViewById(R.id.schedule);
        dataDatabase = DataDatabase.getInstance(this);
        readData();
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void readData(){
        dataDatabase.dataDAO().getlimituserList().observe(this, new Observer<List<WorkData11>>() {
            @Override
            public void onChanged(@Nullable List<WorkData11> data) {
                for(int i = 0; i<data.size(); i++){
                    tvdata.setText(data.get(i).getData());
                }
            }
        });
    }

    private void startWork(){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager11.class)
                .setInputData(createInputData("Inducesmile","Hello world, work completed on time"))
                .setInitialDelay(3, TimeUnit.SECONDS).build();
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
