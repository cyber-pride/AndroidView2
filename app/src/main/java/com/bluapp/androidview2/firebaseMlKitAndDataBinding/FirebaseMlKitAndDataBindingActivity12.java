package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import android.content.Context;
import android.os.Bundle;

import com.bluapp.androidview2.BR;
import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding12Binding;

public class FirebaseMlKitAndDataBindingActivity12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirebaseMlKitAndDataBinding12Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding12);
        ClassObservable userdata = new ClassObservable(this);
        userdata.setEmail("");
        userdata.setPassword("");
        binding.setUser(userdata);
    }

    public static class ClassObservable implements Observable {
        private String email;
        private String password;
        private Context context;
        private PropertyChangeRegistry registry = new PropertyChangeRegistry();

        public ClassObservable(Context context) {
            this.context = context;
        }

        @Bindable
        public String getEmail() {
            if (email == null) {
                return "";
            }
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
            registry.notifyChange(this, BR.email);
        }

        @Bindable({"email"})
        public String getEmailError() {
            if (getEmail().isEmpty()) {
                return "Email field is required";
            }
            return "";
        }

        @Bindable
        public String getPassword() {
            if (password == null) {
                return "";
            }
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
            registry.notifyChange(this, BR.password);
        }

        @Bindable({"password"})
        public String getPasswordError() {
            if (getPassword().isEmpty()) {
                return "Password field is required";
            } else {
                return "";
            }
        }

        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            registry.add(callback);
        }

        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            registry.remove(callback);
        }
    }

}
