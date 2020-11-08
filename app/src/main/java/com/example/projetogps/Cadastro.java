package com.example.projetogps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void salvar(View view) {
    }

    public void novoLocal (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}