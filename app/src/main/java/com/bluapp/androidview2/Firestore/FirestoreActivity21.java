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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class FirestoreActivity21 extends AppCompatActivity {
    private FirebaseFirestore mDatabase;
    private Button delete;
    private CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore21);
        delete = (Button)findViewById(R.id.delete);
        mDatabase = FirebaseFirestore.getInstance();
        collectionReference = mDatabase.collection("AndroidView");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDocument();
            }
        });
    }

    private void deleteDocument(){
        WriteBatch batch = mDatabase.batch();
        DocumentReference documentReference1 = collectionReference.document("Note1");
        batch.delete(documentReference1);

        DocumentReference documentReference2 = collectionReference.document("Note2");
        batch.delete(documentReference2);

        DocumentReference documentReference3 = collectionReference.document("Note3");
        batch.delete(documentReference3);

        batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(FirestoreActivity21.this,"Document Deleted",Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivity21.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Androidview", e.getMessage());
                    }
                });


    }
}
