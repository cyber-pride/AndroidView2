package com.bluapp.androidview2.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseActivity16 extends AppCompatActivity {
    private TextView title;
    private TextView body;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase16);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");
        Query query = mDatabase.orderByChild("title").equalTo("hello").limitToFirst(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot readData: dataSnapshot.getChildren()){
                    title.setText(readData.child("title").getValue().toString());
                    body.setText(readData.child("content").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FirebaseActivity16.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
