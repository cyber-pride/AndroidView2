package com.bluapp.androidview2.Firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreActivity4 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private FirebaseFirestore mDatabase;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore4);
        title = (TextInputEditText)findViewById(R.id.title);
        body = (TextInputEditText)findViewById(R.id.body);
        edit = (Button)findViewById(R.id.edit);
        mDatabase = FirebaseFirestore.getInstance();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtitle = title.getText().toString();
                String enteredbody = body.getText().toString();
                updateDocument(enteredtitle, enteredbody);
            }
        });
    }

    private void updateDocument(String strTitle, String strBody){
        DocumentReference documentReference = mDatabase.collection("AndroidView").document("Post2");
        documentReference.update("title", strTitle);
        documentReference.update("body", strBody)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirestoreActivity4.this,"Document Updated",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivity4.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Androidview", e.getMessage());
                    }
                });
    }
}
