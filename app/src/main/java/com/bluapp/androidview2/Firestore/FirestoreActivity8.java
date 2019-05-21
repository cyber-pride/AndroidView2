package com.bluapp.androidview2.Firestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirestoreActivity8 extends AppCompatActivity {
    private TextView title;
    private TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore8);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        ViewModel8 viewModel8 = ViewModelProviders.of(this).get(ViewModel8.class);
        LiveData<Task<DocumentSnapshot>> liveData = viewModel8.getdataSnapshotLiveData();
        liveData.observe(this, new Observer<Task<DocumentSnapshot>>() {
            @Override
            public void onChanged(Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    title.setText(documentSnapshot.get("title").toString());
                    body.setText(documentSnapshot.get("body").toString());

                }
            }
        });
    }
}
