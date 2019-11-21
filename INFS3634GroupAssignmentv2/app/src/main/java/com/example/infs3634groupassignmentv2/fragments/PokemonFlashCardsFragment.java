package com.example.infs3634groupassignmentv2.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.adapters.FlashCardAdapter;
import com.example.infs3634groupassignmentv2.model.FlashCard;

import java.util.ArrayList;

public class PokemonFlashCardsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FlashCard> flashCardArrayList;

    public PokemonFlashCardsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_pokemon_flash_cards, container, false);

        flashCardArrayList = MainActivity.profile.getFlashCardArrayList();

        recyclerView = frameLayout.findViewById(R.id.rv_pokemonFlashCards);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL,false );
        recyclerView.setLayoutManager(layoutManager);

        FlashCardAdapter flashCardAdapter = new FlashCardAdapter();
        flashCardAdapter.setData(flashCardArrayList);
        recyclerView.setAdapter(flashCardAdapter);

        return frameLayout;
    }

    @Override
    public void onResume(){
        super.onResume();
        FlashCardAdapter flashCardAdapter = new FlashCardAdapter();
        flashCardAdapter.setData(flashCardArrayList);
        recyclerView.setAdapter(flashCardAdapter);
    }

}
