package com.bluapp.androidview2.MapAndService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.bluapp.androidview2.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAndServiceActivity3 extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Marker clientMarker;
    private CameraPosition camPos;
    private CameraUpdate camUpd3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service3);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        clientMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.valueOf(-12.1024174), Double.valueOf(-77.0262274)))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tab_home))
        );
        clientMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Double.valueOf(-12.1024637), Double.valueOf(-77.0242617)))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tab_direction))
        );
        camPos = new CameraPosition.Builder()
                .target(getCenterCoordinate())
                .zoom(17)
                .build();
        camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(camUpd3);
    }

    private LatLng getCenterCoordinate(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(Double.valueOf(-12.1024174), Double.valueOf(-77.0262274)));
        builder.include(new LatLng(Double.valueOf(-12.1024637), Double.valueOf(-77.0242617)));
        LatLngBounds bounds = builder.build();
        return bounds.getCenter();
    }

}
