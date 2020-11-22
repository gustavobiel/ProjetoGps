package com.example.projetogps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cadastro extends AppCompatActivity implements LocationListener {

    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView dataTextView;


    private LocationManager locationManager;
    private LocationListener locationListener;

    double latitudeAtual;
    double longitudeAtual;


    private static final int GPS_REQUEST_PERMISSION_CODE = 1001;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    /**
     * Salvar localizacao
     * @param view
     */
    public void salvar(View view) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        latitudeTextView = findViewById(R.id.editTextLat);
        longitudeTextView = findViewById(R.id.editTextLong);
        dataTextView= findViewById(R.id.editTextData);


        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = location -> {

        };

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //setando data atual
        dataTextView.setText(sdf.format(new Date(System.currentTimeMillis())));



        latitudeTextView.setEnabled(false);
        longitudeTextView.setEnabled(false);
        dataTextView.setEnabled(false);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    10,
                    locationListener
            );
        } else {
            ActivityCompat.requestPermissions
                    (this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            GPS_REQUEST_PERMISSION_CODE
                    );
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
         latitudeAtual = lat;
         longitudeAtual = lon;

        latitudeTextView.setText(String.valueOf(latitudeAtual));
        longitudeTextView.setText(String.valueOf(longitudeAtual));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == GPS_REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            locationListener
                    );
                }
            } else {
                Toast.makeText(this, "Sem GPS!!!", Toast.LENGTH_SHORT).show();
            }
        }

    }



}