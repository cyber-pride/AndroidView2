package com.bluapp.androidview2.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {
    private static final String Log_tag = "FirebaseQueryLiveData";
    private final Query query;
    private final MyValueEventListner listner = new MyValueEventListner();

    public FirebaseQueryLiveData(Query query){
        this.query = query;
    }

    public FirebaseQueryLiveData(DatabaseReference ref){
        this.query = ref;
    }

    @Override
    protected void onActive(){
        Log.d(Log_tag, "onActive");
        query.addValueEventListener(listner);
    }

    @Override
    protected void onInactive(){
        Log.d(Log_tag, "onInactive");
        query.removeEventListener(listner);
    }

    private class MyValueEventListner implements ValueEventListener{

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.d(Log_tag, "Can't listened to query "+query, databaseError.toException());
        }
    }
}
