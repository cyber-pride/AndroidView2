package com.bluapp.androidview2.firebaseMlKitAndDataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ImageView;

import com.bluapp.androidview2.R;
import com.bluapp.androidview2.databinding.ActivityFirebaseMlKitAndDataBinding17Binding;

public class FirebaseMlKitAndDataBindingActivity17 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirebaseMlKitAndDataBinding17Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_firebase_ml_kit_and_data_binding17);
        DataBindingImageView imagedata = new DataBindingImageView();
        imagedata.setProfilepic(R.drawable.profilepic);
        binding.setImageview(imagedata);
    }

    public static class DataBindingImageView {
        private int profilepic;

        public void setProfilepic(int profilepic){
            this.profilepic = profilepic;
        }

        public int getProfilepic(){
            return profilepic;
        }

        @BindingAdapter({"android:image"})
        public static void loadImage(ImageView view, int profilepic){
            view.setImageResource(profilepic);
        }


    }
}
