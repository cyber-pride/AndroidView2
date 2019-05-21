package com.bluapp.androidview2.Firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluapp.androidview2.Firebase.FirebaseActivity1;
import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirestoreActivity1 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private FirebaseFirestore mDatabase;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore1);
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
        mDatabase.collection("AndroidView").document("Post").set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(FirestoreActivity1.this,"Document Inserted",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivity1.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Androidview", e.getMessage());
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();inflater.inflate(R.menu.firestore_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_firestoreactivity2:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity2.class));
                return true;

            case R.id.action_firestoreactivity3:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity3.class));
                return true;

            case R.id.action_firestoreactivity4:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity4.class));
                return true;

            case R.id.action_firestoreactivity5:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity5.class));
                return true;

            case R.id.action_firestoreactivity6:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity6.class));
                return true;

            case R.id.action_firestoreactivity7:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity7.class));
                return true;

            case R.id.action_firestoreactivity8:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity8.class));
                return true;

            case R.id.action_firestoreactivity9:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity9.class));
                return true;

            case R.id.action_firestoreactivity10:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity10.class));
                return true;

            case R.id.action_firestoreactivity11:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity11.class));
                return true;

            case R.id.action_firestoreactivity12:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity12.class));
                return true;

            case R.id.action_firestoreactivity13:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity13.class));
                return true;

            case R.id.action_firestoreactivity14:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity14.class));
                return true;

            case R.id.action_firestoreactivity15:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity15.class));
                return true;

            case R.id.action_firestoreactivity16:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity16.class));
                return true;

            case R.id.action_firestoreactivity17:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity17.class));
                return true;

            case R.id.action_firestoreactivity18:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity18.class));
                return true;

            case R.id.action_firestoreactivity19:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity19.class));
                return true;

            case R.id.action_firestoreactivity20:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity20.class));
                return true;

            case R.id.action_firestoreactivity21:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity21.class));
                return true;

            case R.id.action_firestoreactivity22:
                startActivity(new Intent(FirestoreActivity1.this, FirestoreActivity22.class));
                return true;
        }

        return true;
    }
}
