package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.model.Game;
import com.example.infs3634groupassignmentv2.model.Gym;

public class GameActivity extends AppCompatActivity {

    TextView gameProgress;
    Button startQuizButton;
    Button previousGymButton;
    Button nextGymButton;
    ConstraintLayout currentGym;
    Game game;
    Gym gym;
    int viewableGym;
    int selectedQuiz;
    ImageView gymBadge;
    TextView gymName;
    ImageView gymTile1;
    ImageView gymTile2;
    ImageView gymTile3;
    ImageView gymTile4;
    ImageView gymTile5;
    ImageView gymTile1Lock;
    ImageView gymTile2Lock;
    ImageView gymTile3Lock;
    ImageView gymTile4Lock;
    ImageView gymTile5Lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameProgress = findViewById(R.id.gameProgress);
        startQuizButton = findViewById(R.id.startQuizButton);
        previousGymButton = findViewById(R.id.previousGymButton);
        nextGymButton = findViewById(R.id.nextGymButton);
        currentGym = findViewById(R.id.currentGym);

        game = MainActivity.profile.getGame();
        gym = MainActivity.profile.getGame().getGymArrayList().get(game.getGameProgress());
        viewableGym = game.getGameProgress();
        gameProgress.setText("Current Gym: " + gym.getName());
        startQuizButton.setEnabled(false);

        Toast.makeText(getApplicationContext(), "Select A Trainer To Battle", Toast.LENGTH_LONG).show();

        gymName = currentGym.findViewById(R.id.gymName);
        gymName.setText(gym.getName());
        gymBadge = currentGym.findViewById(R.id.gymBadge);
        if(gym.getGymProgress() == 5){
            gymBadge.setImageResource(gym.getBadge());
        } else{
            gymBadge.setImageResource(R.drawable.layout_circle);
        }
        gymTile1 = currentGym.findViewById(R.id.gymTile1);
        gymTile2 = currentGym.findViewById(R.id.gymTile2);
        gymTile3 = currentGym.findViewById(R.id.gymTile3);
        gymTile4 = currentGym.findViewById(R.id.gymTile4);
        gymTile5 = currentGym.findViewById(R.id.gymTile5);
        gymTile1Lock = currentGym.findViewById(R.id.gymTile1Lock);
        gymTile2Lock = currentGym.findViewById(R.id.gymTile2Lock);
        gymTile3Lock = currentGym.findViewById(R.id.gymTile3Lock);
        gymTile4Lock = currentGym.findViewById(R.id.gymTile4Lock);
        gymTile5Lock = currentGym.findViewById(R.id.gymTile5Lock);

        resetLocks();
        handlePreviousNextButtons();

        previousGymButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gym = MainActivity.profile.getGame().getGymArrayList().get(viewableGym - 1);
                viewableGym--;
                gymName.setText(gym.getName());
                if(gym.getGymProgress() == 5){
                    gymBadge.setImageResource(gym.getBadge());
                } else{
                    gymBadge.setImageResource(R.drawable.layout_circle);
                }
                resetLocks();
                handlePreviousNextButtons();
            }
        });

        nextGymButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gym = MainActivity.profile.getGame().getGymArrayList().get(viewableGym + 1);
                viewableGym++;
                gymName.setText(gym.getName());
                if(gym.getGymProgress() == 5){
                    gymBadge.setImageResource(gym.getBadge());
                } else{
                    gymBadge.setImageResource(R.drawable.layout_circle);
                }
                resetLocks();
                handlePreviousNextButtons();
            }
        });

        startQuizButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(MainActivity.profile.getGame().getGymArrayList().get(viewableGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0).checkIfHeightExists()){
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("selectedGym", viewableGym);
                    intent.putExtra("selectedQuiz", selectedQuiz);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), QuizSplashscreenActivity.class);
                    intent.putExtra("selectedGym", viewableGym);
                    intent.putExtra("selectedQuiz", selectedQuiz);
                    startActivity(intent);
                }
            }
        });

    }

    private void handlePreviousNextButtons(){
        if(gym.getName().equals(MainActivity.profile.getGame().getGymArrayList().get(0).getName())){
            previousGymButton.setVisibility(View.GONE);
        } else if(gym.getName().equals(MainActivity.profile.getGame().getGymArrayList().get(MainActivity.profile.getGame().getGymArrayList().size() - 1).getName())){
            nextGymButton.setVisibility(View.GONE);
        } else{
            previousGymButton.setVisibility(View.VISIBLE);
            nextGymButton.setVisibility(View.VISIBLE);
        }

        handlePlayableQuizzes(0,gymTile1Lock,gymTile1);
        handlePlayableQuizzes(1,gymTile2Lock,gymTile2);
        handlePlayableQuizzes(2,gymTile3Lock,gymTile3);
        handlePlayableQuizzes(3,gymTile4Lock,gymTile4);
        handlePlayableQuizzes(4,gymTile5Lock,gymTile5);
    }

    private void handlePlayableQuizzes(final int gymProgress, ImageView gymTileLock, final ImageView gymTile){
        if(game.getGameProgress() >= viewableGym && gym.getGymProgress() >= gymProgress){
            gymTileLock.setVisibility(View.GONE);
            gymTile.setEnabled(true);
            gymTile.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    selectedQuiz = gymProgress;
                    resetTiles();
                    gymTile.setImageResource(R.drawable.ic_radio_button_checked_black_24dp);
                    startQuizButton.setEnabled(true);
                }
            });
        }
    }

    private void resetLocks(){
        resetTiles();
        gymTile1Lock.setVisibility(View.VISIBLE);
        gymTile2Lock.setVisibility(View.VISIBLE);
        gymTile3Lock.setVisibility(View.VISIBLE);
        gymTile4Lock.setVisibility(View.VISIBLE);
        gymTile5Lock.setVisibility(View.VISIBLE);
        gymTile1.setEnabled(false);
        gymTile2.setEnabled(false);
        gymTile3.setEnabled(false);
        gymTile4.setEnabled(false);
        gymTile5.setEnabled(false);
    }

    private void resetTiles(){
        gymTile1.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        gymTile2.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        gymTile3.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        gymTile4.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
        gymTile5.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
    }




}
