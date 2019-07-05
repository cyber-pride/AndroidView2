package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluapp.androidview2.R;

public class NavFragment18 extends Fragment {
    private TextView tabName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nav_fragment18, container, false);
        tabName = (TextView) rootView.findViewById(R.id.tabName);
        String argtabname = getArguments().getString("tabName");
        tabName.setText(argtabname);
        return rootView;
    }


}
