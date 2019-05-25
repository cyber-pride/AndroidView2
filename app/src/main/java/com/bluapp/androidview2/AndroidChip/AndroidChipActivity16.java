package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class AndroidChipActivity16 extends AppCompatActivity {
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip16);
        chipGroup = (ChipGroup)findViewById(R.id.user);
        Chip maleChip = getChip(chipGroup, "Male");
        Chip femaleChip = getChip(chipGroup, "Female");
        chipGroup.addView(maleChip);
        chipGroup.addView(femaleChip);
    }

    private Chip getChip(final ChipGroup chipGroup, String text){
        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.chip));
        int paddingDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(chip);
            }
        });
        return chip;
    }
}
