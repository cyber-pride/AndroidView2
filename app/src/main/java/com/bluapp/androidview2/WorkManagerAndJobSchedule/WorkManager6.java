package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManager6 extends Worker {
    private Data data;
    public WorkManager6(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Thread.sleep(5000);
            data = new Data.Builder()
                    .putString("data", "Hello world, work completed on time")
                    .build();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.success(data);
    }
}
