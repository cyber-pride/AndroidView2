package com.bluapp.androidview2.DeviceFeatures;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class DeviceFeaturesActivity6 extends AppCompatActivity {
    private TextView renderHtml;
    String htmlText = "<h2>What is Android?</h2>\n" +
            "<p>Android is an open source and Linux-based <b>Operating System</b> for mobile devices such as smartphones and tablet computers. Android was developed by the <i>Open Handset Alliance</i>, led by Google, and other companies.</p>\n" +
            "<p>Android offers a unified approach to application development for mobile devices which means developers need only develop for Android, and their applications should be able to run on different devices powered by Android.</p>\n" +
            "<p>The first beta version of the Android Software Development Kit (SDK) was released by Google in 2007 whereas the first commercial version, Android 1.0, was released in September 2008.</p>";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features6);
        renderHtml = (TextView) findViewById(R.id.renderHtml);
        renderHtml.setText(HtmlCompat.fromHtml(htmlText, 0));
    }
}
