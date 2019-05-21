package com.bluapp.androidview2.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bluapp.androidview2.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shreyaspatil.firebase.recyclerpagination.DatabasePagingOptions;
import com.shreyaspatil.firebase.recyclerpagination.FirebaseRecyclerPagingAdapter;
import com.shreyaspatil.firebase.recyclerpagination.LoadingState;

public class FirebaseActivity9 extends AppCompatActivity {
    private RecyclerView list;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerPagingAdapter<Data, DataViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase9);
        list = (RecyclerView) findViewById(R.id.list);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("AndroidView");
        list.setHasFixedSize(true);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        list.setLayoutManager(mManager);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(5)
                .setPageSize(10)
                .build();
        DatabasePagingOptions<Data> options = new DatabasePagingOptions.Builder<Data>()
                .setLifecycleOwner(this)
                .setQuery(mDatabase, config, Data.class)
                .build();

        mAdapter = new FirebaseRecyclerPagingAdapter<Data, DataViewHolder>(options) {
            @NonNull
            @Override
            public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview, parent, false));
            }
            @Override
            protected void onBindViewHolder(@NonNull DataViewHolder holder, int position, @NonNull Data model) {
                holder.setItem(model);
            }
            @Override
            protected void onLoadingStateChanged(@NonNull LoadingState state) {
                switch (state) {
                    case LOADING_INITIAL:
                    case LOADING_MORE:
                        // Do your loading animation
                        Toast.makeText(FirebaseActivity9.this,"Loading..",Toast.LENGTH_LONG).show();
                        break;
                    case LOADED:
                        // Stop Animation
                        break;
                    case FINISHED:
                        //Reached end of Data set
                        Toast.makeText(FirebaseActivity9.this,"End of data..",Toast.LENGTH_LONG).show();
                        break;
                    case ERROR:
                        break;
                }
            }
            @Override
            protected void onError(@NonNull DatabaseError databaseError) {
                super.onError(databaseError);
                databaseError.toException().printStackTrace();
            }
        };

        //Set Adapter to RecyclerView
        list.setAdapter(mAdapter);

        }

    //Start Listening Adapter
    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    //Stop Listening Adapter
    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewContent = itemView.findViewById(R.id.content);
        }
        public void setItem(Data data){
            textViewTitle.setText(data.getTitle());
            textViewContent.setText(data.getContent());
        }
    }

}
