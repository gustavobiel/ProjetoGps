package com.example.projetogps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = location -> {

        };
    }

    @Override
    protected void onStart() {
        super.onStart();
            if(ActivityCompat.checkSelfPermission
                    (this.Manifest.permission.ACESS_FILE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5000, 5,
                        locationListener
                );
            }
        else{
            ActivityCompat.requestPermissions
                    (this,
                    new String [] {Manifest.permission.ACCESS_FINE_LOCATION}
                    1043
                    );
            }

    }

    public void novoLugar(View view) {
    }
}