package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.fragments.PokemonFlashCardsFragment;
import com.example.infs3634groupassignmentv2.fragments.PokemonInfoFragment;

public class PokemonActivity extends AppCompatActivity {

    private Pokemon pokemonObject;
    ConstraintLayout pokemonHeader;
    TextView pokemonName1;
    ImageView pokemonImage1;
    ConstraintLayout pokemonHeader1;
    Button infoButton;
    Button flashCardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Intent intent = getIntent();
        pokemonObject = (Pokemon) intent.getSerializableExtra("pokemonObject");

        pokemonHeader = findViewById(R.id.pokemonHeader);
        pokemonName1 = pokemonHeader.findViewById(R.id.pokemonName1);
        pokemonImage1 = pokemonHeader.findViewById(R.id.pokemonImage1);
        pokemonHeader1 = findViewById(R.id.pokemonHeader1);
        infoButton = pokemonHeader1.findViewById(R.id.infoButton);
        flashCardsButton = pokemonHeader1.findViewById(R.id.flashCardButton);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot_pokemon, new PokemonInfoFragment());
        fragmentTransaction.commit();

        String name = pokemonObject.getName();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        pokemonName1.setText(name);
        Glide.with(getApplicationContext()).load(pokemonObject.getSprites().getFront_default()).into(pokemonImage1);

        infoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_slot_pokemon, new PokemonInfoFragment()).commit();
            }
        });

        flashCardsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_slot_pokemon, new PokemonFlashCardsFragment()).commit();

            }
        });
    }

    public Pokemon getPokemonObject() {
        return pokemonObject;
    }

    public void setPokemonObject(Pokemon pokemonObject) {
        this.pokemonObject = pokemonObject;
    }
}
