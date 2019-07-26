package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.bluapp.androidview2.BR;
import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding11Binding;

public class FirebaseMlKitAndDataBindingActivity11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirebaseMlKitAndDataBinding11Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding11);
        ObservableBase userdata = new ObservableBase(this);
        userdata.setEmail("");
        userdata.setPassword("");
        binding.setUser(userdata);
    }

    public static class ObservableBase extends BaseObservable {
        private String email;
        private String password;
        private Context context;

        public ObservableBase(Context context) {
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
            notifyPropertyChanged(BR.email);
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
            notifyPropertyChanged(BR.password);
        }

        @Bindable({"password"})
        public String getPasswordError() {
            if (getPassword().isEmpty()) {
                return "Password field is required";
            } else {
                return "";
            }
        }
    }
}
