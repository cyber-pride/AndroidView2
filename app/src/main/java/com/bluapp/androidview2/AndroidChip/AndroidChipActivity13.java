package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;

public class AndroidChipActivity13 extends AppCompatActivity {
    private AppCompatEditText phone;
    private int SpannedLength = 0;
    private int chipLength = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip13);
        phone = (AppCompatEditText) findViewById(R.id.phone);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             if(s.length() == SpannedLength - chipLength){
                 SpannedLength = s.length();
             }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() - SpannedLength == chipLength){
                    ChipDrawable chip = ChipDrawable.createFromResource(AndroidChipActivity13.this, R.xml.chip);
                    chip.setText(s.subSequence(SpannedLength, s.length()));
                    chip.setBounds(0, 0, chip.getIntrinsicWidth(), chip.getIntrinsicHeight());
                    ImageSpan span = new ImageSpan(chip);
                    s.setSpan(span, SpannedLength, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    SpannedLength = s.length();
                }

            }
        });
    }
}
