package com.bluapp.androidview2.MapAndService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

public abstract class AbstractService extends Service {
    static final int MSG_REGISTER_CLIENT = 9991;
    static final int MSG_UNREGISTER_CLIENT = 9992;
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();
    final Messenger mMessenger = new Messenger(new IncomingHandler());
    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    Log.i("MyService", "Client registered: " + msg.replyTo);
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    Log.i("MyService", "Client un-registered: " + msg.replyTo);
                    mClients.remove(msg.replyTo);
                    break;
                default:
                    onReceiveMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        onStartService();
        Log.i("MyService", "Service Started.");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "Received start id " + startId + ": " + intent);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        onStopService();
        Log.i("MyService", "Service Stopped.");
    }
    protected void send(Message msg) {
        for (int i = mClients.size() - 1; i >= 0; i--) {
            try {
                Log.i("MyService", "Sending message to clients: " + msg);
                mClients.get(i).send(msg);
            } catch (RemoteException e) {
                Log.e("MyService", "Client is dead. Removing from list: " + i);
                mClients.remove(i);
            }
        }
    }
    public abstract void onStartService();
    public abstract void onStopService();
    public abstract void onReceiveMessage(Message msg);

}