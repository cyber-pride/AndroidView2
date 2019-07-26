package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding15Binding;

public class FirebaseMlKitAndDataBindingActivity15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirebaseMlKitAndDataBinding15Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding15);
        ClickHandler clickHandler = new ClickHandler(this);
        binding.setHandlers(clickHandler);
    }

    public class ClickHandler{
        Context context;
        public ClickHandler(Context context){
            this.context = context;
        }

        public void onButtonClicked(View view){
            Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
