package com.example.artbookjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artbookjava.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtHolder>{

    ArrayList<Art> artList;

    public ArtAdapter(ArrayList<Art> artList)
    {
        this.artList=artList;
    }

    @NonNull
    @Override
    public ArtHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
       return new ArtHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.recylerViewTextView.setText(artList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),ActivityArt.class);
                intent.putExtra("info","old");
                intent.putExtra("id",artList.get(position).id);
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return artList.size();
    }

    public static class ArtHolder extends RecyclerView.ViewHolder
    {
        private RecyclerRowBinding binding;
        public ArtHolder(RecyclerRowBinding binding ) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
