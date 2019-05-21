package com.bluapp.androidview2.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity1 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private DatabaseReference mDatabase;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase1);
        title = (TextInputEditText)findViewById(R.id.title);
        body = (TextInputEditText)findViewById(R.id.body);
        submit = (Button)findViewById(R.id.submit);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtitle = title.getText().toString();
                String enteredbody = body.getText().toString();
                saveData(enteredtitle, enteredbody);
            }
        });


    }
    private void saveData(String strTitle, String strBody){
        DatabaseReference newEntry = mDatabase.push();
        newEntry.child("title").setValue(strTitle);
        newEntry.child("content").setValue(strBody)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirebaseActivity1.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FirebaseActivity1.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.firebase_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_firebaseactivity2:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity2.class));
                return true;

            case R.id.action_firebaseactivity3:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity3.class));
                return true;

            case R.id.action_firebaseactivity4:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity4.class));
                return true;

            case R.id.action_firebaseactivity5:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity5.class));
                return true;

            case R.id.action_firebaseactivity6:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity6.class));
                return true;

            case R.id.action_firebaseactivity7:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity7.class));
                return true;

            case R.id.action_firebaseactivity8:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity8.class));
                return true;

            case R.id.action_firebaseactivity9:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity9.class));
                return true;

            case R.id.action_firebaseactivity10:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity10.class));
                return true;

            case R.id.action_firebaseactivity11:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity11.class));
                return true;

            case R.id.action_firebaseactivity12:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity12.class));
                return true;

            case R.id.action_firebaseactivity13:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity13.class));
                return true;

            case R.id.action_firebaseactivity14:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity14.class));
                return true;

            case R.id.action_firebaseactivity15:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity15.class));
                return true;

            case R.id.action_firebaseactivity16:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity16.class));
                return true;

            case R.id.action_firebaseactivity17:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity17.class));
                return true;

            case R.id.action_firebaseactivity18:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity18.class));
                return true;

            case R.id.action_firebaseactivity19:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity19.class));
                return true;

            case R.id.action_firebaseactivity20:
                startActivity(new Intent(FirebaseActivity1.this, FirebaseActivity20.class));
                return true;
        }

        return true;
    }

}
