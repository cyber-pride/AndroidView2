package com.bluapp.androidview2.DeviceFeatures;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.bluapp.androidview2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService11 extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.d("AndroidView2", "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getData() != null) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "AndroidView2")
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("body"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
            Log.d("AndroidView2", "Message Notification Title: "+remoteMessage.getData().get("title")+" Message Notification Body: "+remoteMessage.getData().get("body"));
        }

    }
}
