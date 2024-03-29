package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bluapp.androidview2.R;

public class WorkManager2 extends Worker {

    public WorkManager2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        showNotification("Inducesmile", "Hello world, work completed");
        return Result.success();
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
