package com.bluapp.androidview2.DeviceFeatures;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluapp.androidview2.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceFeaturesActivity7 extends AppCompatActivity {
    private RecyclerView list;
    private adapter recyclerAdapter;
    private Cursor cursor;
    private List<Contact> StoreContacts ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features7);
        list = (RecyclerView) findViewById(R.id.list);
        StoreContacts = new ArrayList<Contact>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        GetContactsList();
    }

    public void GetContactsList(){
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            StoreContacts.add(new Contact(name, phonenumber));
        }
        recyclerAdapter = new adapter(DeviceFeaturesActivity7.this, StoreContacts);
        list.setAdapter(recyclerAdapter);
        cursor.close();

    }

    public class Contact{
        private String name;
        private String phonenumber;

        public Contact(String name, String phonenumber){
            this.name = name;
            this.phonenumber = phonenumber;
        }

        public String getName(){
            return name;
        }

        public String getPhonenumber(){
            return phonenumber;
        }
    }

    private class adapter extends RecyclerView.Adapter<adapter.myViewHolder> {
        Context context;
        List<Contact> mData;

        public adapter(Context context, List<Contact> data) {
            this.context = context;
            this.mData = data;
        }

        @NonNull
        @Override
        public adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.devicefeatures_adapter7, parent, false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull adapter.myViewHolder holder, int position) {
            holder.nameTxt.setText(mData.get(position).getName());
            holder.phonenumberTxt.setText(mData.get(position).getPhonenumber());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder {
            TextView nameTxt;
            TextView phonenumberTxt;

            public myViewHolder(View itemView) {
                super(itemView);
                nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
                phonenumberTxt = (TextView) itemView.findViewById(R.id.phonenumberTxt);
            }
        }
    }



}
