package com.bluapp.androidview2.BluetoothAndNavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BluetoothAndNavigationActivity13 extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_and_navigation13);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
