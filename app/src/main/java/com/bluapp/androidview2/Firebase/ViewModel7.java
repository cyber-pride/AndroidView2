package com.bluapp.androidview2.Firebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewModel7 extends ViewModel {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");
    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(mDatabase);

    @NonNull
    public LiveData<DataSnapshot> getdataSnapshotLiveData(){
        return liveData;
    }

}
