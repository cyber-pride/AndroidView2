package com.bluapp.androidview2.MapAndService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.bluapp.androidview2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAndServiceActivity1 extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private BitmapDescriptor bitmapDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_service1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).icon(bitmapDescriptor).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapandservice_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_mapandservice2:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity2.class));
                return true;

            case R.id.action_mapandservice3:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity3.class));
                return true;

            case R.id.action_mapandservice4:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity4.class));
                return true;

            case R.id.action_mapandservice5:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity5.class));
                return true;

            case R.id.action_mapandservice6:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity6.class));
                return true;

            case R.id.action_mapandservice7:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity7.class));
                return true;

            case R.id.action_mapandservice8:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity8.class));
                return true;

            case R.id.action_mapandservice9:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity9.class));
                return true;

            case R.id.action_mapandservice10:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity10.class));
                return true;

            case R.id.action_mapandservice11:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity11.class));
                return true;

            case R.id.action_mapandservice12:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity12.class));
                return true;

            case R.id.action_mapandservice13:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity13.class));
                return true;

            case R.id.action_mapandservice14:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity14.class));
                return true;

            case R.id.action_mapandservice15:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity15.class));
                return true;

            case R.id.action_mapandservice16:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity16.class));
                return true;

            case R.id.action_mapandservice17:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity17.class));
                return true;

            case R.id.action_mapandservice18:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity18.class));
                return true;

            case R.id.action_mapandservice19:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity19.class));
                return true;

            case R.id.action_mapandservice20:
                startActivity(new Intent(MapAndServiceActivity1.this, MapAndServiceActivity20.class));
                return true;
        }

        return true;
    }
}
