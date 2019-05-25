package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class AndroidChipActivity11 extends AppCompatActivity {
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip11);
        chipGroup = (ChipGroup)findViewById(R.id.gender);
        final ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        list.add(buttonView.getText().toString());
                    }else{
                        list.remove(buttonView.getText().toString());
                    }
                    if(!list.isEmpty()){
                        Toast.makeText(AndroidChipActivity11.this, list.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
