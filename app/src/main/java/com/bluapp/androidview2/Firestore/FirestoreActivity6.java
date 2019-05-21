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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FirestoreActivity6 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private FirebaseFirestore mDatabase;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore6);
        title = (TextInputEditText)findViewById(R.id.title);
        body = (TextInputEditText)findViewById(R.id.body);
        submit = (Button)findViewById(R.id.submit);
        mDatabase = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtitle = title.getText().toString();
                String enteredbody = body.getText().toString();
                saveDocument(enteredtitle, enteredbody);
            }
        });
    }

    private void saveDocument(String strTitle, String strBody){
        Map<String, Object> post = new HashMap<>();
        post.put("title", strTitle);
        post.put("body", strBody);
        post.put("date", new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        mDatabase.collection("AndroidView").document("Post3").set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirestoreActivity6.this,"Document Inserted",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivity6.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Androidview", e.getMessage());
                    }
                });
    }

}