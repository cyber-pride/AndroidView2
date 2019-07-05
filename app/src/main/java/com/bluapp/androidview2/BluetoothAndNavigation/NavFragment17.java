package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bluapp.androidview2.R;

public class NavFragment17 extends Fragment {
    private String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_fragment17, container, false);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        if(state!=null){
            state.putString("website","Inducesmile");
            Toast.makeText(getActivity(),"Fragment Save State",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null){
            String website = savedInstanceState.getString("website");
            Toast.makeText(getActivity(),"Fragment Restore State: "+website,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


}
