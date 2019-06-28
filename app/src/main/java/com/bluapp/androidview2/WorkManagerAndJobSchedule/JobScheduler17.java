package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.bluapp.androidview2.R;

public class JobScheduler17 extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showNotification("Inducesmile", "Job finished");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private void showNotification(String title, String task){
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("inducesmile", "inducesmile", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),"inducesmile")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(1, notification.build());
    }
}
