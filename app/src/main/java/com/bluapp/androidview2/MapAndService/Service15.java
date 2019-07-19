package com.bluapp.androidview2.MapAndService;

import android.os.Message;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class Service15 extends AbstractService {
    public static final int MSG_INCREMENT = 1;
    public static final int MSG_COUNTER = 2;
    private Timer timer = new Timer();
    private int counter = 0, incrementby = 1;

    @Override
    public void onStartService() {
        timer.scheduleAtFixedRate(new TimerTask(){ public void run() {onTimerTick();}}, 0, 250L);
    }

    @Override
    public void onStopService() {
        if (timer != null) {timer.cancel();}
        counter=0;
        Log.i("MyService", "Service Stopped.");
    }

    @Override
    public void onReceiveMessage(Message msg) {
        if (msg.what == MSG_INCREMENT) {
            incrementby = msg.arg1;
        }
    }
    private void onTimerTick() {
        try {
            counter += incrementby;
            send(Message.obtain(null, MSG_COUNTER, counter, 0));
        }
        catch (Throwable t) { //you should always ultimately catch all exceptions in timer tasks.
            Log.e("TimerTick", "Timer Tick Failed.", t);
        }
    }
}