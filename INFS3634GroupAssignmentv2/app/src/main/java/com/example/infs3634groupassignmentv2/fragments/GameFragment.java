package com.example.infs3634groupassignmentv2.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.activities.GameActivity;

public class GameFragment extends Fragment {

    Button startGameButton;
    TextView gameFragmentBody;

    public GameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_game, container, false);

        gameFragmentBody = frameLayout.findViewById(R.id.gameFragmentBody);

        gameFragmentBody.setText("Battle pokemon trainers and gym leaders to progress through the 7 " +
                "gyms, earn badges and show off your knowledge of the pokemon universe! " +
                "\n \n Each battle requires a score of 5/5 to progress onto the next stage. \n " +
                "\n Best of luck trainers!");

        startGameButton = frameLayout.findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        return frameLayout;
    }

}
