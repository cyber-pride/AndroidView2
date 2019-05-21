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

public class FirebaseActivity11 extends AppCompatActivity {
    private TextView title;
    private TextView body;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase11);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");
        Query query = mDatabase.orderByChild("title");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot readData: dataSnapshot.getChildren()){
                        Data data = readData.getValue(Data.class);
                        title.setText(data.getTitle());
                        body.setText(data.getContent());
                    }
                }else{
                    Toast.makeText(FirebaseActivity11.this,"Data Empty",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FirebaseActivity11.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
