package com.bluapp.androidview2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.bluapp.androidview2.AndroidChip.AndroidChipActivity1;
import com.bluapp.androidview2.Firebase.FirebaseActivity1;
import com.bluapp.androidview2.Firestore.FirestoreActivity1;

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

    }
