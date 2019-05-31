package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

public class AndroidChipActivity3 extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    private Chip chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip3);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        chip = new Chip(this);
        ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(this, null, 0, R.style.Widget_MaterialComponents_Chip_Action);
        chip.setChipDrawable(chipDrawable);
        chip.setText("User");
        chip.setChipIcon(getDrawable(R.drawable.ic_user));
        chip.setIconStartPadding(5);
        relativeLayout.addView(chip);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AndroidChipActivity3.this, "User Click",Toast.LENGTH_LONG).show();
            }
        });
    }
}
