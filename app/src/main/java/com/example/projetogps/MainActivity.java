package com.example.projetogps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    }

    class MinhaImplementacao implements LocationListener{


        @Override
        public void onLocationChanged(@NonNull Location location) {

        }
    }


    public void novoLugar(View view) {
    }
}