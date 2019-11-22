package com.example.infs3634groupassignmentv2.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.GameActivity;
import com.example.infs3634groupassignmentv2.activities.PokedexActivity;


public class HomeFragment extends Fragment {

    ConstraintLayout homeGameSlot;
    ConstraintLayout homePokedexSlot;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_home, container, false);

        homeGameSlot = frameLayout.findViewById(R.id.homeGameSlot);
        homePokedexSlot = frameLayout.findViewById(R.id.homePokedexSlot);

        populateHomeSlot(homeGameSlot,
                "Game",
                R.drawable.pokeball,
                "Complete quizzes to progress through gyms and earn badges.");

        homeGameSlot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        populateHomeSlot(homePokedexSlot,
                "Pokedex",
                R.drawable.pokedex,
                "Search through the pokedex for pokemon.");

        homePokedexSlot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), PokedexActivity.class);
                startActivity(intent);
            }
        });

        return frameLayout;
    }

    private static void populateHomeSlot(ConstraintLayout constraintLayout, String slotName,
                                         int slotImage, String slotDescription){
        TextView homeSlotName = constraintLayout.findViewById(R.id.slot_name);
        ImageView homeSlotImage = constraintLayout.findViewById(R.id.slot_image);
        TextView homeSlotDescription = constraintLayout.findViewById(R.id.slot_description);
        homeSlotName.setText(slotName);
        homeSlotImage.setImageResource(slotImage);
        homeSlotDescription.setText(slotDescription);
    }

}
