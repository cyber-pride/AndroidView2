package com.bluapp.androidview2.DeviceFeatures;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bluapp.androidview2.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DeviceFeaturesActivity13 extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener,
        ResultCallback<Status>{

    private static final int REQ_PERMISSION = 101;
    private SupportMapFragment mapFragment;
    private static final String TAG = "AndroidView2";
    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private final int UPDATE_INTERVAL =  1000;
    private final int FASTEST_INTERVAL = 900;
    private Marker locallocationMarker;
    private Marker geoFenceMarker;
    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "AndroidView2 Geofence";
    private static final float GEOFENCE_RADIUS = 600.0f; // in meters
    private PendingIntent geoFencePendingIntent;
    private final int GEOFENCE_REQ_CODE = 0;
    private Circle geoFenceLimits;
    private static final String NOTIFICATION_MSG = "NOTIFICATION MSG";
    public static Intent initNotificationIntent(Context context, String msg) {
        Intent intent = new Intent( context, DeviceFeaturesActivity13.class );
        intent.putExtra( NOTIFICATION_MSG, msg );
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_features13);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initGoogleApi();
    }

    private void initGoogleApi() {
        Log.d(TAG, "initGoogleApi()");
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private Geofence initGeofence(LatLng latLng, float radius) {
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_REQ_ID)
                .setCircularRegion( latLng.latitude, latLng.longitude, radius)
                .setExpirationDuration(GEO_DURATION)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    private GeofencingRequest initGeofenceRequest(Geofence geofence) {
        return new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofence(geofence)
                .build();
    }

    private PendingIntent createGeofencePendingIntent() {
        if ( geoFencePendingIntent != null )
            return geoFencePendingIntent;
        Intent intent = new Intent( this, GeofenceService.class);
        return PendingIntent.getService(
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );
    }

    private void addGeofence(GeofencingRequest request) {
        if (checkPermission())
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    request,
                    createGeofencePendingIntent()
            ).setResultCallback(this);
    }

    @Override
    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult: " + status);
        if ( status.isSuccess() ) {
            drawGeofence();
        } else {
            //Failed request
        }
    }

    private void drawGeofence() {
        if (geoFenceLimits != null)
            geoFenceLimits.remove();
        CircleOptions circleOptions = new CircleOptions()
                .center( geoFenceMarker.getPosition())
                .strokeColor(Color.argb(50, 70,70,70))
                .fillColor( Color.argb(100, 150,150,150) )
                .radius( GEOFENCE_RADIUS );
        geoFenceLimits = map.addCircle(circleOptions);
    }

    private void startGeofence() {
        Log.i(TAG, "startGeofence()");
        if(geoFenceMarker != null) {
            Geofence geofence = initGeofence(geoFenceMarker.getPosition(), GEOFENCE_RADIUS);
            GeofencingRequest geofenceRequest = initGeofenceRequest(geofence);
            addGeofence( geofenceRequest );
        } else {
            Log.e(TAG, "Geofence marker is null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.geofence_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.action_startgeofence:
                startGeofence();
                return true;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(TAG, "onMapClick(" + latLng + ")");
        markerForGeofence(latLng);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClick: " + marker.getPosition());
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);
        Log.d(TAG, "onMapReady() Method");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected()");
        getLastLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed()");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged ["+location+"]");
        lastLocation = location;
        toastCurentLocation(location);
    }

    private void getLastLocation() {
        Log.d(TAG, "getLastLocation()");
        if (checkPermission()) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {
                Log.i(TAG, "LasKnown location. " + "Long: " + lastLocation.getLongitude() + " | Lat: " + lastLocation.getLatitude());
                startLocationUpdates();
            } else {
                Log.w(TAG, "No location retrieved yet");
                startLocationUpdates();
            }
        }else{
            requestPermission();
        }
    }

    private void startLocationUpdates(){
        Log.i(TAG, "startLocationUpdates()");
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        if (checkPermission())
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    private void toastCurentLocation(Location location) {
        Toast.makeText(DeviceFeaturesActivity13.this,"Lat: " + location.getLatitude()+" Long: " + location.getLongitude(), Toast.LENGTH_LONG).show();
        markerForLocation(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED );
    }

    private void requestPermission() {
        Log.d(TAG, "requestPermission()");
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, REQ_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_PERMISSION: {
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    getLastLocation();
                } else {
                    Log.w(TAG, "permissionsDenied()");
                }
                break;
            }
        }
    }

    private void markerForLocation(LatLng latLng) {
        Log.i(TAG, "markerLocation("+latLng+")");
        String title = latLng.latitude + ", " + latLng.longitude;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title);
        if(map!=null){
            if(locallocationMarker != null)
                locallocationMarker.remove();
            locallocationMarker = map.addMarker(markerOptions);
            float zoom = 14f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);
        }
    }

    private void markerForGeofence(LatLng latLng) {
        Log.i(TAG, "markerForGeofence(" + latLng + ")");
        String title = latLng.latitude + ", " + latLng.longitude;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title(title);
        if (map != null) {
            if (geoFenceMarker != null)
                geoFenceMarker.remove();
            geoFenceMarker = map.addMarker(markerOptions);
        }
    }



}
