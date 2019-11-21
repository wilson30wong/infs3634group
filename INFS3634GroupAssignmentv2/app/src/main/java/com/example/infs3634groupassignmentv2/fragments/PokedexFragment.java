package com.example.infs3634groupassignmentv2.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.GameActivity;
import com.example.infs3634groupassignmentv2.activities.PokedexActivity;

public class PokedexFragment extends Fragment {

    Button startPokedexButton;

    public PokedexFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_pokedex, container, false);

        startPokedexButton = frameLayout.findViewById(R.id.startPokedexButton);
        startPokedexButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), PokedexActivity.class);
                startActivity(intent);
            }
        });

        return frameLayout;
    }



}
