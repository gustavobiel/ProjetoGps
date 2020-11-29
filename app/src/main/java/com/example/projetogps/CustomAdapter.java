package com.example.projetogps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    public CustomAdapter(ListActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;
    }

    ListActivity listActivity;
    List<Model> modelList;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_layout, viewGroup, false);

       ViewHolder viewHolder = new ViewHolder(itemView);

       viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
           @Override
           public void onItemClick(View view, int position) {

               String local = modelList.get(position).getLocal();
               String data = modelList.get(position).getData();
               String lat = modelList.get(position).getLat();
               String lon = modelList.get(position).getLon();
               String desc = modelList.get(position).getDescricao();
               Toast.makeText(listActivity, local+"\n"+data+"\n"+lat+"\n"+lon+"\n"+desc, Toast.LENGTH_SHORT).show();


           }

           @Override
           public void onItemLongClick(View view, int position) {
               //isso vai ser chamado quando usado o long click

               //criando um alerta
               AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);
               //opção de delete
               String[] options = {"Deletar"};
               builder.setItems(options, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            // deletar
                            listActivity.deleteData(position);
                        }
                   }
               }).create().show();

           }
       });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mLocalTv.setText(modelList.get(i).getLocal());
        viewHolder.mDataTv.setText(modelList.get(i).getData());
        viewHolder.mLatTv.setText(modelList.get(i).getLat());
        viewHolder.mLonTv.setText(modelList.get(i).getLon());
        viewHolder.mDescTv.setText(modelList.get(i).getDescricao());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
