package com.example.dogsretrofit;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogsretrofit.databinding.ItemDogBinding;
import com.squareup.picasso.Picasso;

public class DogViewHolder extends RecyclerView.ViewHolder {

    private final ItemDogBinding binding ;
    //View view;

    public DogViewHolder(View view) {
        super(view);
        binding = ItemDogBinding.bind(view);
    }

    public void bind(String image){
        Picasso.get().load(image).into(binding.imDog);
    }
}
