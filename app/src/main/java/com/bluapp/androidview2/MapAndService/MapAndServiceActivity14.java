package com.bluapp.androidview2.MapAndService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class MapAndServiceActivity14 extends AppCompatActivity {
    private Button btnIntentService;
    private ServiceResultReceiver serviceResultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service14);
        btnIntentService = (Button)findViewById(R.id.btnIntentService);

        btnIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceResultReceiver = new ServiceResultReceiver(new Handler());
                String url = "https://inducesmile.com/wp-content/uploads/2019/01/inducesmilelog.png";
                Intent intent = new Intent(MapAndServiceActivity14.this, Service14.class);
                intent.putExtra("receiver", serviceResultReceiver);
                intent.putExtra("url", url);
                startService(intent);
            }
        });
    }

    class ServiceResultReceiver extends ResultReceiver {
        public ServiceResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            switch (resultCode){
                case Service14.DOWNLOAD_SUCCESS:
                    String file_path = resultData.getString("file_path");
                        Toast.makeText(getApplicationContext(), "image downloaded via IntentService is successfully", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.onReceiveResult(resultCode, resultData);
        }
    }

}
