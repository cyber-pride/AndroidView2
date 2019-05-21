package com.bluapp.androidview2.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bluapp.androidview2.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseActivity4 extends AppCompatActivity {
    private TextInputEditText title;
    private TextInputEditText body;
    private DatabaseReference mDatabase;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase4);
        title = (TextInputEditText)findViewById(R.id.title);
        body = (TextInputEditText)findViewById(R.id.body);
        edit = (Button)findViewById(R.id.edit);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtitle = title.getText().toString();
                String enteredbody = body.getText().toString();
                editData(enteredtitle, enteredbody);
            }
        });


    }
    private void editData(String strTitle, final String strBody){
        Query editQuery = mDatabase.orderByChild("title").equalTo(strTitle);
        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot edtData: dataSnapshot.getChildren()){
                    edtData.getRef().child("content").setValue(strBody);
                }
                Toast.makeText(FirebaseActivity4.this,"Data Edited",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FirebaseActivity4.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
