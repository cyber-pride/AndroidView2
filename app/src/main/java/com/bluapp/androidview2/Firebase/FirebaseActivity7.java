package com.bluapp.androidview2.Firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.TextView;

import com.bluapp.androidview2.R;
import com.google.firebase.database.DataSnapshot;

public class FirebaseActivity7 extends AppCompatActivity {
    private TextView title;
    private TextView body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase7);
        title = (TextView)findViewById(R.id.title);
        body = (TextView)findViewById(R.id.body);
        ViewModel7 viewModel7 = ViewModelProviders.of(this).get(ViewModel7.class);
        LiveData<DataSnapshot>liveData = viewModel7.getdataSnapshotLiveData();
        liveData.observe((LifecycleOwner) this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                if(dataSnapshot != null){
                    for(DataSnapshot readData: dataSnapshot.getChildren()){
                        Data data = readData.getValue(Data.class);
                         title.setText(data.getTitle());
                         body.setText(data.getContent());
                    }
                }
            }
        });


    }


}
