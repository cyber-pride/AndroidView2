package com.bluapp.androidview2.DeviceFeatures;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.bluapp.androidview2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Log.d("AndroidView2", "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "AndroidView2")
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
            Log.d("AndroidView2", "Message Notification Title: "+remoteMessage.getNotification().getTitle()+" Message Notification Body: "+remoteMessage.getNotification().getBody());
        }

    }
}
