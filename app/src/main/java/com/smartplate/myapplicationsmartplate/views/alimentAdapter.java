package com.smartplate.myapplicationsmartplate.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartplate.myapplicationsmartplate.Aliments;
import com.smartplate.myapplicationsmartplate.R;

import java.util.List;

public class alimentAdapter extends RecyclerView.Adapter<alimentViewHolder> {
    private List <Aliments> aliments;
    private RecyclerViewClickListener recyclerViewClickListener;


    public alimentAdapter(List<Aliments>aliments, RecyclerViewClickListener recyclerViewClickListener) {
        this.aliments = aliments;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public alimentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);
        return new  alimentViewHolder(view,recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull alimentViewHolder holder, int position) {
        holder.display(aliments.get(position));

    }

    @Override
    public int getItemCount() { return aliments.size(); }

    public interface RecyclerViewClickListener {
        void onClick (int position);

    }

}
