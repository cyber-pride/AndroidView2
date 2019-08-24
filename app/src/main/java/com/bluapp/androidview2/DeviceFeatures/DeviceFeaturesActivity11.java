package com.bluapp.androidview2.DeviceFeatures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class DeviceFeaturesActivity11 extends AppCompatActivity {
    private TextView fcmToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features11);
        fcmToken = (TextView) findViewById(R.id.fcmToken);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String token = task.getResult().getToken();
                            fcmToken.setText("Fcm Token: "+token);
                            Log.d("AndroidView2", "Fcm Token: "+token);
                        }
                    }
                });

    }
}
