package com.example.dogsretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {

    private final List<String> images;

    public DogAdapter(List<String> images){
        this.images = images;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        String item = images.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


}
