package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding13Binding;

public class FirebaseMlKitAndDataBindingActivity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ml_kit_and_data_binding13);
        CountryObject country = new CountryObject();
        ActivityFirebaseMlKitAndDataBinding13Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding13);
        binding.setCountry(country);
        country.name.set("Nigeria, Sweden, India e.t.c");
    }

    public class CountryObject{
        public final ObservableField<String> name = new ObservableField<>();
    }
}
