package com.bluapp.androidview2.Firestore;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewModel8 extends ViewModel {
    private static final FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();
    private final FirestoreQueryLiveData liveData = new FirestoreQueryLiveData(mDatabase.collection("AndroidView").document("Post"));

    @NonNull
    public LiveData<Task<DocumentSnapshot>> getdataSnapshotLiveData(){
        return liveData;
    }

}
