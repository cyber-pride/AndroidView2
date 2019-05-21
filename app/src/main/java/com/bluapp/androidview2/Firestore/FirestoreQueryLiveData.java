package com.bluapp.androidview2.Firestore;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class FirestoreQueryLiveData extends LiveData<Task<DocumentSnapshot>> {
    private static final String Log_tag = "FirebaseQueryLiveData";
    private final DocumentReference documentReference;
    private final MyValueEventListner listner = new MyValueEventListner();

    public FirestoreQueryLiveData(DocumentReference ref){
        this.documentReference = ref;
    }


    @Override
    protected void onActive(){
        Log.d(Log_tag, "onActive");
        documentReference.get().addOnCompleteListener(listner);
    }

    @Override
    protected void onInactive(){
        Log.d(Log_tag, "onInactive");
        documentReference.get().addOnCompleteListener(listner);
    }

    private class MyValueEventListner implements OnCompleteListener<DocumentSnapshot>{

        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            setValue(task);
        }

    }

}
