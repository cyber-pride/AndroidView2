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

public class FirebaseActivity19 extends AppCompatActivity {
    private TextInputEditText title;
    private DatabaseReference mDatabase;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase19);
        title = (TextInputEditText)findViewById(R.id.title);
        delete = (Button)findViewById(R.id.delete);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredtitle = title.getText().toString();
                deleteData(enteredtitle);
            }
        });

    }

    private void deleteData(String strTitle){
        Query deleteQuery = mDatabase.orderByChild("title").equalTo(strTitle);
        deleteQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot delData: dataSnapshot.getChildren()){
                    delData.getRef().removeValue();
                }
                Toast.makeText(FirebaseActivity19.this,"Data Deleted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FirebaseActivity19.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
