package com.example.infs3634groupassignmentv2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.GameActivity;
import com.example.infs3634groupassignmentv2.activities.PokemonActivity;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.api.PokemonSprites;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonArrayList;

    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon, parent,
                false);
        PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(view);
        return pokemonViewHolder;
    }

    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        final Pokemon pokemonObject = pokemonArrayList.get(position);
        final Context context = holder.view.getContext();
        String name = pokemonObject.getName();
        if(name.equals("No Pokemon Found")) {
            holder.pokemonName.setText(name);
            holder.view.setEnabled(false);
        } else{
            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            holder.pokemonName.setText(name);
            Glide.with(context).load(pokemonObject.getSprites().getFront_default()).into(holder.pokemonImage);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemonObject",pokemonObject);
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public void setData(ArrayList<Pokemon> data) {
        this.pokemonArrayList = data;
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView pokemonName;
        public ImageView pokemonImage;

        public PokemonViewHolder(View v) {
            super(v);
            view = v;
            pokemonName = v.findViewById(R.id.pokemonName);
//            pokemonImage = v.findViewById(R.id.pokemonImage);
        }
    }
}
