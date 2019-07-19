package com.bluapp.androidview2.MapAndService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BrService4 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, Service18.class));
    }
}