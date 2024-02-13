package com.example.deneme;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deneme.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedHolder> {

    ArrayList<Kullanici> kullaniiciListesi;
    public FeedAdapter(ArrayList<Kullanici> kullaniiciListesi)
    {
        this.kullaniiciListesi=kullaniiciListesi;
    }
    @NonNull
    @Override
    public FeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FeedHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return kullaniiciListesi.size();
    }

    public class FeedHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBinding binding;

        public FeedHolder(@NonNull RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
