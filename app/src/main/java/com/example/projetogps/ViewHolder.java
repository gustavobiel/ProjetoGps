package com.example.projetogps;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mLocalTv, mDataTv, mLatTv, mLonTv, mDescTv;
    View mView;

    public ViewHolder(@NonNull View itemView){
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

        mLocalTv = itemView.findViewById(R.id.rLocalTv);
        mDataTv = itemView.findViewById(R.id.rDataTv);
        mLatTv = itemView.findViewById(R.id.rLatTv);
        mLonTv = itemView.findViewById(R.id.rLonTv);
        mDescTv = itemView.findViewById(R.id.rDescTv);
     }

     private ViewHolder.ClickListener mClickListener;

     public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
     }
     public void setOnClickListener(ViewHolder.ClickListener clickListener){
         mClickListener = clickListener;
     }
}
