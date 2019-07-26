package com.bluapp.androidview2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.AndroidChart.AndroidChartActivity1;
import com.bluapp.androidview2.AndroidChip.AndroidChipActivity1;
import com.bluapp.androidview2.BluetoothAndNavigation.BluetoothAndNavigationActivity1;
import com.bluapp.androidview2.Canvas.CanvasActivity1;
import com.bluapp.androidview2.Firebase.FirebaseActivity1;
import com.bluapp.androidview2.firebaseMlKitAndDataBinding.FirebaseMlKitAndDataBindingActivity1;
import com.bluapp.androidview2.Firestore.FirestoreActivity1;
import com.bluapp.androidview2.MapAndService.MapAndServiceActivity1;
import com.bluapp.androidview2.WorkManagerAndJobSchedule.WorkManagerAndJobScheduleActivity1;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }

    public void Firebase(View view){
        startActivity(new Intent(ViewActivity.this, FirebaseActivity1.class));
    }

    public void Firestore(View view){
        startActivity(new Intent(ViewActivity.this, FirestoreActivity1.class));
    }

    public void AndroidChip(View view){
        startActivity(new Intent(ViewActivity.this, AndroidChipActivity1.class));
    }

    public void Canvas(View view){
        startActivity(new Intent(ViewActivity.this, CanvasActivity1.class));
    }

    public void AndroidChart(View view){
        startActivity(new Intent(ViewActivity.this, AndroidChartActivity1.class));
    }

    public void WorkManagerAndJobSchedule(View view){
        startActivity(new Intent(ViewActivity.this, WorkManagerAndJobScheduleActivity1.class));
    }

    public void BluetoothAndNavigation(View view){
        startActivity(new Intent(ViewActivity.this, BluetoothAndNavigationActivity1.class));
    }

    public void MapAndService(View view){
        startActivity(new Intent(ViewActivity.this, MapAndServiceActivity1.class));
    }

    public void FirebaseMlKitAndDataBinding(View view){
        startActivity(new Intent(ViewActivity.this, FirebaseMlKitAndDataBindingActivity1.class));
    }

    }
