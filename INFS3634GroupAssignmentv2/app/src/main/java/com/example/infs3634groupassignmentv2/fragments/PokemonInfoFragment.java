package com.example.infs3634groupassignmentv2.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.PokemonActivity;
import com.example.infs3634groupassignmentv2.api.Pokemon;

public class PokemonInfoFragment extends Fragment {

    public static Pokemon pokemonObject;
    TextView pokemonHeight;
    TextView pokemonWeight;
    TextView pokemonFlavorText;
    TextView pokemonTypes;
    TextView pokemonAbilities;
    TextView pokemonHabitat;
    TextView pokemonGenus;
    TextView pokemonShape;
    TextView pokemonGeneration;
    TextView pokemonStatsMeta;
    TextView pokemonStatsData;

    public PokemonInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_pokemon_info, container, false);

        pokemonObject = ((PokemonActivity)getActivity()).getPokemonObject();

        pokemonHeight = frameLayout.findViewById(R.id.pokemonHeight);
        pokemonWeight = frameLayout.findViewById(R.id.pokemonWeight);
        pokemonFlavorText = frameLayout.findViewById(R.id.pokemonFlavorText);
        pokemonTypes = frameLayout.findViewById(R.id.pokemonTypes);
        pokemonAbilities = frameLayout.findViewById(R.id.pokemonAbilities);
        pokemonHabitat = frameLayout.findViewById(R.id.pokemonHabitat);
        pokemonGenus = frameLayout.findViewById(R.id.pokemonGenus);
        pokemonShape = frameLayout.findViewById(R.id.pokemonShape);
        pokemonGeneration = frameLayout.findViewById(R.id.pokemonGeneration);
        pokemonStatsMeta = frameLayout.findViewById(R.id.pokemonStatsMeta);
        pokemonStatsData = frameLayout.findViewById(R.id.pokemonStatsData);

        double height = ((double) pokemonObject.getHeight()) / 10;
        pokemonHeight.setText("Height: " + height + "m");
        double weight = ((double) pokemonObject.getWeight()) / 10;
        pokemonWeight.setText("Weight: " + weight + "kg");
        String types = "Types: ";
        for(int i = 0; i < pokemonObject.getTypes().size(); i++){
            String typei = pokemonObject.getTypes().get(i).getType().getName();
            typei = typei.substring(0,1).toUpperCase() + typei.substring(1).toLowerCase();
            types = types.concat("\n" + typei);
        }
        pokemonTypes.setText(types);
        String abilities = "Abilities: ";
        for(int i = 0; i < pokemonObject.getAbilities().size(); i++){
            String abilityi = pokemonObject.getAbilities().get(i).getAbility().getName();
            abilityi = abilityi.substring(0,1).toUpperCase() + abilityi.substring(1).toLowerCase();
            abilities = abilities.concat("\n" + abilityi);
        }
        pokemonAbilities.setText(abilities);
        String statsMeta = "Stats: ";
        for(int i = 0; i < pokemonObject.getStats().size(); i++){
            String statsMetai = pokemonObject.getStats().get(i).getStat().getName();
            statsMetai = statsMetai.substring(0,1).toUpperCase() + statsMetai.substring(1).toLowerCase();
            statsMeta = statsMeta.concat("\n" + statsMetai);
        }
        pokemonStatsMeta.setText(statsMeta);
        String statsData = "";
        for(int i = 0; i < pokemonObject.getStats().size(); i++){
            int statsDatai = pokemonObject.getStats().get(i).getBase_stat();
            statsData = statsData.concat("\n" + statsDatai);
        }
        pokemonStatsData.setText(statsData);

        String flavorText = "";
        flavorText = pokemonObject.getSpecies().findFlavorTextEntry(pokemonObject.getSpecies().getFlavor_text_entries(), "en").getFlavor_text();
        flavorText = flavorText.replace("\n", " ");
        pokemonFlavorText.setText(flavorText);
        String habitat = "";
        if (pokemonObject.getSpecies().getHabitat() != null) {
            habitat = pokemonObject.getSpecies().getHabitat().getName();
            habitat = habitat.substring(0, 1).toUpperCase() + habitat.substring(1).toLowerCase();
        }
        pokemonHabitat.setText("Habitat: \n" + habitat);
        String genus = "";
        genus = pokemonObject.getSpecies().findGenus(pokemonObject.getSpecies().getGenera(), "en").getGenus();
        pokemonGenus.setText("Genus: \n" + genus);
        String shape = "";
        shape = pokemonObject.getSpecies().getShape().getName();
        shape = shape.substring(0, 1).toUpperCase() + shape.substring(1).toLowerCase();
        pokemonShape.setText("Shape: \n" + shape);
        String generation = "";
        generation = pokemonObject.getSpecies().getGeneration().getName();
        switch (generation) {
            case "generation-i": {
                generation = "I";
            }
            break;
            case "generation-ii": {
                generation = "II";
            }
            break;
            case "generation-iii": {
                generation = "III";
            }
            break;
            case "generation-iv": {
                generation = "IV";
            }
            break;
            case "generation-v": {
                generation = "V";
            }
            break;
            case "generation-vi": {
                generation = "VI";
            }
            break;
            case "generation-vii": {
                generation = "VII";
            }
            break;
        }
        pokemonGeneration.setText("Generation: " + generation);

        return frameLayout;
    }


}
