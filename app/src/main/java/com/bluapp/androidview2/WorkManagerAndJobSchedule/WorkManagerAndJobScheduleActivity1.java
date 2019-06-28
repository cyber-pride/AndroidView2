package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bluapp.androidview2.R;

public class WorkManagerAndJobScheduleActivity1 extends AppCompatActivity {
    private Button downloadBtn;
    private ProgressBar progressBar;
    private TextView progress_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_and_job_schedule1);
        downloadBtn = (Button) findViewById(R.id.download);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progress_tv = (TextView) findViewById(R.id.progress_tv);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresStorageNotLow(true)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(DownloadWorker.class)
                .setConstraints(constraints).build();;
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
        LiveDataHelper.getInstance().observePercentage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setProgress(integer);
                progress_tv.setText(integer+"/100");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.workmanagerandjobschedule_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_workmanagerandjobscheduleactivity2:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity2.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity3:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity3.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity4:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity4.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity5:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity5.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity6:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity6.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity7:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity7.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity8:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity8.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity9:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity9.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity10:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity10.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity11:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity11.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity12:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity12.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity13:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity13.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity14:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity14.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity15:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity15.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity16:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity16.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity17:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity17.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity18:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity18.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity19:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity19.class));
                return true;

            case R.id.action_workmanagerandjobscheduleactivity20:
                startActivity(new Intent(WorkManagerAndJobScheduleActivity1.this, WorkManagerAndJobScheduleActivity20.class));
                return true;
        }

        return true;
    }
}
