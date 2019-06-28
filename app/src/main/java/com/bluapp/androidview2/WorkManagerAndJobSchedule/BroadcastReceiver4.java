package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class BroadcastReceiver4 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkManager4.class).build();
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);
    }
}
