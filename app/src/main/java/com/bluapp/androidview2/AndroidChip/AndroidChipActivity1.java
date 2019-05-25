package com.bluapp.androidview2.AndroidChip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class AndroidChipActivity1 extends AppCompatActivity {
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_chip1);
        chipGroup = (ChipGroup)findViewById(R.id.gender);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = chipGroup.findViewById(i);
                if(chip != null){
                    Toast.makeText(AndroidChipActivity1.this, chip.getText().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.androidchip_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_androidchipactivity2:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity2.class));
                return true;

            case R.id.action_androidchipactivity3:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity3.class));
                return true;

            case R.id.action_androidchipactivity4:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity4.class));
                return true;

            case R.id.action_androidchipactivity5:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity5.class));
                return true;

            case R.id.action_androidchipactivity6:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity6.class));
                return true;

            case R.id.action_androidchipactivity7:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity7.class));
                return true;

            case R.id.action_androidchipactivity8:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity8.class));
                return true;

            case R.id.action_androidchipactivity9:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity9.class));
                return true;

            case R.id.action_androidchipactivity10:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity10.class));
                return true;

            case R.id.action_androidchipactivity11:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity11.class));
                return true;

            case R.id.action_androidchipactivity12:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity12.class));
                return true;

            case R.id.action_androidchipactivity13:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity13.class));
                return true;

            case R.id.action_androidchipactivity14:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity14.class));
                return true;

            case R.id.action_androidchipactivity15:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity15.class));
                return true;

            case R.id.action_androidchipactivity16:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity16.class));
                return true;

            case R.id.action_androidchipactivity17:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity17.class));
                return true;

            case R.id.action_androidchipactivity18:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity18.class));
                return true;

            case R.id.action_androidchipactivity19:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity19.class));
                return true;

            case R.id.action_androidchipactivity20:
                startActivity(new Intent(AndroidChipActivity1.this, AndroidChipActivity20.class));
                return true;
        }

        return true;
    }
}
