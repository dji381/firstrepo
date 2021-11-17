package com.smartplate.myapplicationsmartplate.views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartplate.myapplicationsmartplate.Aliments;
import com.smartplate.myapplicationsmartplate.R;

public class alimentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView nomAliments;
    alimentAdapter.RecyclerViewClickListener recyclerViewClickListener;
    public alimentViewHolder(@NonNull View itemView, alimentAdapter.RecyclerViewClickListener recyclerViewClickListener) {
        super(itemView);
        nomAliments = (TextView)itemView.findViewById(R.id.name_aliment);
        this.recyclerViewClickListener = recyclerViewClickListener;
        itemView.setOnClickListener(this);
    }
    public void display (Aliments aliments){
        nomAliments.setText(aliments.getName());
    }

    @Override
    public void onClick(View v) {
        recyclerViewClickListener.onClick(getAdapterPosition());

    }
}
