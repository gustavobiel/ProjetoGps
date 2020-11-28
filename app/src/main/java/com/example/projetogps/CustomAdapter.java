package com.example.projetogps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    public CustomAdapter(ListActivity listActivity, List<Model> modelList) {
        this.listActivity = listActivity;
        this.modelList = modelList;
    }

    ListActivity listActivity;
    List<Model> modelList;
    Context context;
    private static ClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.model_layout, viewGroup, false);
        return new ViewHolder(v);
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

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CustomAdapter.clickListener = clickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView mLocalTv, mDataTv, mLatTv, mLonTv, mDescTv;
        View mView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mLocalTv = itemView.findViewById(R.id.rLocalTv);
            mDataTv = itemView.findViewById(R.id.rDataTv);
            mLatTv = itemView.findViewById(R.id.rLatTv);
            mLonTv = itemView.findViewById(R.id.rLonTv);
            mDescTv = itemView.findViewById(R.id.rDescTv);
        }


        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
