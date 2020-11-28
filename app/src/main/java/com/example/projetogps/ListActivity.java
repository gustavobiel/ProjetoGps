package com.example.projetogps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecyclerView;
    //layout manager recyclerview
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton mAddBtn;

    //instancia do firestore
    FirebaseFirestore db;

    CustomAdapter adapter;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //inicializando firestore
        db = FirebaseFirestore.getInstance();

        //inicializando a views
        mRecyclerView = findViewById(R.id.recycler_view);
        mAddBtn = findViewById(R.id.addBtn);

        //propriedades da recyclerview
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        pd = new ProgressDialog(this);

        showData();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, Cadastro.class));
                finish();
            }
        });

    }

    private void showData() {
        pd.setTitle("Loading...");
        pd.show();

        db.collection("Documents")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        pd.dismiss();

                        for (DocumentSnapshot doc:task.getResult()){
                            Model model = new Model(doc.getString("id"),
                                    doc.getString("local"),
                                    doc.getString("data"),
                                    doc.getString("latitude"),
                                    doc.getString("longitude"),
                                    doc.getString("descrição"));
                            modelList.add(model);

                        }

                        adapter = new CustomAdapter(ListActivity.this, modelList);
                        adapter.setOnItemClickListener(new CustomAdapter.ClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                Log.d("RESULTADO", "onItemClick position: " + position);
                            }

                            @Override
                            public void onItemLongClick(int position, View v) {
                                Log.d("RESULTADO", "onItemLongClick pos = " + position);
                            }
                        });

                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();

                        Toast.makeText(ListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}