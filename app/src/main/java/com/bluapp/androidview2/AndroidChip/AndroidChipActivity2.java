package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class AndroidChipActivity2 extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip2);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        chipGroup = new ChipGroup(this);
        chipGroup.setSingleSelection(true);
        String[] genders = {"Male", "Female"};
        for(String gender : genders){
            Chip chip = new Chip(this);
            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(this, null, 0, R.style.Widget_MaterialComponents_Chip_Filter);
            chip.setChipDrawable(chipDrawable);
            chip.setText(gender);
            chipGroup.addView(chip);
        }
        relativeLayout.addView(chipGroup);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = chipGroup.findViewById(i);
                if(chip != null){
                    Toast.makeText(AndroidChipActivity2.this, chip.getText().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
