package com.example.infs3634groupassignmentv2.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.GameActivity;
import com.example.infs3634groupassignmentv2.activities.PokedexActivity;

public class PokedexFragment extends Fragment {

    Button startPokedexButton;
    TextView pokedexFragmentBody;

    public PokedexFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_pokedex, container, false);

        pokedexFragmentBody = frameLayout.findViewById(R.id.pokedexFragmentBody);

        pokedexFragmentBody.setText( "Using the Pokedex, you are able to search up any pokemon using" +
                " their pokedex number, name or type and view the details of their stats \n \n" +
                " You are also able to write your own notes to help you study for the quiz, through " +
                "the flash cards function!");


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
