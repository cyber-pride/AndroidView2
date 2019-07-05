package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluapp.androidview2.R;
import com.google.firebase.database.DataSnapshot;

import javax.annotation.Nullable;

public class NavFragment20 extends Fragment {
    private TextView name;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Viewmodel20 viewmodel20 = ViewModelProviders.of(getActivity()).get(Viewmodel20.class);
        LiveData<String> liveData = viewmodel20.observeItemname();
        liveData.observe((LifecycleOwner) this, new Observer<String>() {
            @Override
            public void onChanged(String string) {
                if(string != null){
                    name.setText(string);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_fragment20, container, false);
        name = (TextView) rootView.findViewById(R.id.name);

        return rootView;
    }


}
