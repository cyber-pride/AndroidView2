package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity2 extends AppCompatActivity {
    private EditText pnEdt;
    private Button callBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features2);
        pnEdt = (EditText) findViewById(R.id.pnEdt);
        callBtn = (Button) findViewById(R.id.callBtn);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = pnEdt.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phonenumber));
                startActivity(intent);
            }
        });
    }
}
