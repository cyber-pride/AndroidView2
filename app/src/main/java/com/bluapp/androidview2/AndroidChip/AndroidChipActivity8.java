package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class AndroidChipActivity8 extends AppCompatActivity {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip8);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        ChipGroup chipGroup = new ChipGroup(this);
        String[] genres = {"Comedy", "Adventure", "Thriller", "Action", "Animation"};
        for(String genre : genres){
            Chip chip = new Chip(this);
            chip.setText(genre);
            chipGroup.addView(chip);
        }
        relativeLayout.addView(chipGroup);
    }
}
