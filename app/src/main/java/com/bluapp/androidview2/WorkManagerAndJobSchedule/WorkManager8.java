package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.bluapp.androidview2.R;

public class WorkManager8 extends Worker {
    private Data outputData;
    public WorkManager8(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        outputData = new Data.Builder()
                .putString("data", "Hello world, work completed on time")
                .build();
        String title = getInputData().getString("title");
        String message = getInputData().getString("message");
        showNotification(title, message);
        return Result.success(outputData);
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
