package com.bluapp.androidview2.Firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bluapp.androidview2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FirebaseActivity14 extends AppCompatActivity {

    private RecyclerView list;
    private static DatabaseReference mDatabase;
    private Query mQueryCurrent;
    private FirebaseRecyclerAdapter<Data, EntryViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase14);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");
        mDatabase.keepSynced(true);
        mQueryCurrent = mDatabase.orderByChild("title");
        list = (RecyclerView) findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
                .setQuery(mDatabase, Data.class)
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Data, EntryViewHolder>(options) {
            @NonNull
            @Override
            public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview, parent, false);
                return new EntryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i, @NonNull Data data) {
                entryViewHolder.setTitle(data.getTitle());
                entryViewHolder.setContent(data.getContent());
            }
        };

        list.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }

    public static class EntryViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView e_title;
        TextView e_content;
        public EntryViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            e_title = (TextView) mView.findViewById(R.id.title);
            e_title.setText(title);

        }
        public void setContent(String content){
            e_content = (TextView) mView.findViewById(R.id.content);
            e_content.setText(content);
        }

    }
}
