package com.example.projetogps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;


public class Cadastro extends AppCompatActivity {
    public static final int DEFAULT_UPDATE_INTERVAL = 3;
    public static final int FAST_UPDATE_INTERVAL = 5;
    EditText editTextLocal, editTextData, editTextLat, editTextLong, editTextDesc;

    // API do google para serviço de localização
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        editTextLocal = findViewById(R.id.editTextLocal);
        editTextData = findViewById(R.id.editTextData);
        editTextLat = findViewById(R.id.editTextLat);
        editTextLong = findViewById(R.id.editTextLong);
        editTextDesc = findViewById(R.id.editTextDesc);

        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000 * DEFAULT_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(1000 * FAST_UPDATE_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }
    public void salvar(View view) {
    }




}