package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity3 extends AppCompatActivity {
    private EditText toEdt;
    private EditText subjectEdt;
    private EditText messageEdt;
    private Button sendEmailBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features3);
        toEdt = (EditText) findViewById(R.id.toEdt);
        subjectEdt = (EditText) findViewById(R.id.subjectEdt);
        messageEdt = (EditText) findViewById(R.id.messageEdt);
        sendEmailBtn = (Button) findViewById(R.id.sendEmailBtn);

        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = toEdt.getText().toString();
                String subject = subjectEdt.getText().toString();
                String message = messageEdt.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
    }
}
