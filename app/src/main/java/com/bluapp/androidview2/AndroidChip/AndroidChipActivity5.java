package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;

public class AndroidChipActivity5 extends AppCompatActivity {
    private Chip chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip5);
        chip = (Chip)findViewById(R.id.user);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidChipActivity5.this, "User Click",Toast.LENGTH_LONG).show();
            }
        });
    }
}
