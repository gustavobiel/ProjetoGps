package com.example.projetogps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cadastro extends AppCompatActivity implements LocationListener {

    EditText meditTextLocal, meditTextData, meditTextLat, meditTextLong, meditTextDesc;
    Button mbuttonSalvar, mbuttonLista;

    //Progress Dialog
    ProgressDialog pd;

    //Firestore instancia
    FirebaseFirestore db;

    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView dataTextView;


    private LocationManager locationManager;
    private LocationListener locationListener;

    double latitudeAtual;
    double longitudeAtual;


    private static final int GPS_REQUEST_PERMISSION_CODE = 1001;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //inicializando views com xml
        meditTextLocal = findViewById(R.id.editTextLocal);
        meditTextData = findViewById(R.id.editTextData);
        meditTextLat = findViewById(R.id.editTextLat);
        meditTextLong = findViewById(R.id.editTextLong);
        meditTextDesc = findViewById(R.id.editTextDesc);
        mbuttonSalvar = findViewById(R.id.buttonSalvar);
        mbuttonLista = findViewById(R.id.buttonLista);

        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //botao clicavel para upload de data
        mbuttonSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //input data
                String local = meditTextLocal.getText().toString().trim();
                String data = meditTextData.getText().toString().trim();
                String lat = meditTextLat.getText().toString().trim();
                String lon = meditTextLong.getText().toString().trim();
                String desc = meditTextDesc.getText().toString().trim();

                //funcao para upload data
                uploadData(local, data, lat, lon, desc);
            }
        });

        // botao clicavel para iniciar Lista
        mbuttonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cadastro.this, ListActivity.class));
                finish();
            }
        });


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

    private void uploadData(String local, String data, String lat, String lon, String desc) {
        pd.setTitle("Adicionando ao Firestore");
        pd.show();
        //random id para cada dado para ser armazenado
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("local", local);
        doc.put("data", data);
        doc.put("latitude", lat);
        doc.put("longitude", lon);
        doc.put("descrição", desc);

        //add this data
        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //vai ser chamado quando dado for add com sucesso

                        pd.dismiss();
                        Toast.makeText(Cadastro.this, "Adicionado...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //vai ser chamado quando tiver algum erro
                        pd.dismiss();
                        Toast.makeText(Cadastro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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