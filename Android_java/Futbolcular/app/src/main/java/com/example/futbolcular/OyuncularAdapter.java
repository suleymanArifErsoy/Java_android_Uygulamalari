package com.example.futbolcular;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futbolcular.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class OyuncularAdapter extends RecyclerView.Adapter<OyuncularAdapter.OyuncularHolder>
{

    ArrayList<OyuncuOzellikleri> oyuncularListesi;
    public OyuncularAdapter(ArrayList<OyuncuOzellikleri> oyuncuOzellikleri)
    {
        this.oyuncularListesi=oyuncuOzellikleri;
    }

    @NonNull
    @Override
    public OyuncularHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OyuncularHolder(recyclerRowBinding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull OyuncularHolder holder, int position) {

        holder.binding.recylerViewTextView.setText(oyuncularListesi.get(position).isim);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),ActivityDetayi.class);
                intent.putExtra("oyuncular",oyuncularListesi.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return oyuncularListesi.size();
    }

    public class OyuncularHolder extends RecyclerView.ViewHolder
    {

        private RecyclerRowBinding binding;
        public OyuncularHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
