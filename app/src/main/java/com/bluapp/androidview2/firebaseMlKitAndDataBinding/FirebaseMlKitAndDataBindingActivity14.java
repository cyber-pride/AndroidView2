package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayMap;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding14Binding;

public class FirebaseMlKitAndDataBindingActivity14 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ObservableArrayMap<String, Object> country = new ObservableArrayMap<>();
        ActivityFirebaseMlKitAndDataBinding14Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding14);
        binding.setCountry(country);
        country.put("name", "Nigeria, Sweden, India e.t.c");
    }
}
