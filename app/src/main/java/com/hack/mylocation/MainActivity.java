package com.hack.mylocation;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.hack.mylocation.utils.AddActivityPermissionsDispatcher;
import com.hack.mylocation.utils.GPSTracker;

import permissions.dispatcher.NeedsPermission;

public class MainActivity extends AppCompatActivity {

    Button getLocation;
    TextView myLocation;
    private GPSTracker gps;

    private double latitude = 0f, longitude = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocation = findViewById(R.id.getLocation);
        myLocation = findViewById(R.id.myLocation);

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddActivityPermissionsDispatcher.getLatLngWithCheck(MainActivity.this);

                LatLng latLng = new LatLng(latitude, longitude);

                myLocation.setText("latitude=" + latitude + " longitude=" + longitude);

            }
        });
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void getLatLng(){
        gps = new GPSTracker(MainActivity.this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
    }
}
