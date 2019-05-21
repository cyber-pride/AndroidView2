package com.bluapp.androidview2.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity2 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private DatabaseReference mDatabase;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase2);
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
                        Toast.makeText(FirebaseActivity2.this,"Data Inserted",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FirebaseActivity2.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
