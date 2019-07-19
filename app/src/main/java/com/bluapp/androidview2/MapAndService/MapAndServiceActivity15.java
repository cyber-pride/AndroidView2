package com.bluapp.androidview2.MapAndService;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.R;


public class MapAndServiceActivity15 extends AppCompatActivity {
    private TextView tvCounter;
    private Button btnStart;
    private Button btnStop;
    private ServiceManager service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service15);
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        this.service = new ServiceManager(this, Service15.class, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Service15.MSG_COUNTER:
                        // Receive counter value from service 1
                        tvCounter.setText("Counter: " + msg.arg1);
                        break;

                    default:
                        super.handleMessage(msg);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.start();
                Toast.makeText(MapAndServiceActivity15.this, "Service Started", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.stop();
                Toast.makeText(MapAndServiceActivity15.this, "Service Stopped", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            service.unbind();
        } catch (Throwable t) {
        }
    }

    public class ServiceManager {
        private Class<? extends AbstractService> mServiceClass;
        private Context mActivity;
        private boolean mIsBound;
        private Messenger mService = null;
        private Handler mIncomingHandler = null;
        private final Messenger mMessenger = new Messenger(new IncomingHandler());
        private class IncomingHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                if (mIncomingHandler != null) {
                    Log.i("ServiceHandler", "Incoming message. Passing to handler: " + msg);
                    mIncomingHandler.handleMessage(msg);
                }
            }
        }
        private ServiceConnection mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {
                mService = new Messenger(service);
                Log.i("ServiceHandler", "Attached.");
                try {
                    Message msg = Message.obtain(null, AbstractService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    mService.send(msg);
                } catch (RemoteException e) {
                }
            }
            public void onServiceDisconnected(ComponentName className) {
                mService = null;
                Log.i("ServiceHandler", "Disconnected.");
            }
        };
        public ServiceManager(Context context, Class<? extends AbstractService> serviceClass, Handler incomingHandler) {
            this.mActivity = context;
            this.mServiceClass = serviceClass;
            this.mIncomingHandler = incomingHandler;
            if (isRunning()) {
                doBindService();
            }
        }
        public void start() {
            doStartService();
            doBindService();
        }
        public void stop() {
            doUnbindService();
            doStopService();
        }
        public void unbind() {
            doUnbindService();
        }

        public boolean isRunning() {
            ActivityManager manager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (mServiceClass.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }
            return false;
        }
        public void send(Message msg) throws RemoteException {
            if (mIsBound) {
                if (mService != null) {
                    mService.send(msg);
                }
            }
        }
        private void doStartService() {
            mActivity.startService(new Intent(mActivity, mServiceClass));
        }
        private void doStopService() {
            mActivity.stopService(new Intent(mActivity, mServiceClass));
        }
        private void doBindService() {
            mActivity.bindService(new Intent(mActivity, mServiceClass), mConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
        }
        private void doUnbindService() {
            if (mIsBound) {
                if (mService != null) {
                    try {
                        Message msg = Message.obtain(null, AbstractService.MSG_UNREGISTER_CLIENT);
                        msg.replyTo = mMessenger;
                        mService.send(msg);
                    } catch (RemoteException e) {
                    }
                }
                mActivity.unbindService(mConnection);
                mIsBound = false;
                Log.i("ServiceHandler", "Unbinding.");
            }
        }
    }

}
