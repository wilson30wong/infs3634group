package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.adapters.PokemonAdapter;
import com.example.infs3634groupassignmentv2.model.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    Button startQuizButton1;
    RecyclerView recyclerView;
    int selectedQuiz;
    int selectedGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        startQuizButton1 = findViewById(R.id.startQuizButton1);
        recyclerView = findViewById(R.id.rv_quizPokemon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        selectedGym = intent.getIntExtra("selectedGym", -1);
        selectedQuiz = intent.getIntExtra("selectedQuiz", -1);

        startQuizButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizInProgressActivity.class);
                if (MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().size() == 5) {
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().clear();
                    generateNewQuizQuestions();
                } else {
                    generateNewQuizQuestions();
                }
                intent.putExtra("selectedGym", selectedGym);
                intent.putExtra("selectedQuiz", selectedQuiz);
                startActivity(intent);
            }
        });

        PokemonAdapter pokemonAdapter = new PokemonAdapter();
        pokemonAdapter.setData(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList());
        recyclerView.setAdapter(pokemonAdapter);
    }

    public void generateNewQuizQuestions(){
        ArrayList<Integer> randomPokemonOrder = new ArrayList<Integer>(5);
        for (int i = 0; i < 5; i++) {
            randomPokemonOrder.add(i);
        }
        Collections.shuffle(randomPokemonOrder);

        ArrayList<Integer> randomQuestionOrder = new ArrayList<Integer>(5);
        for(int i = 0; i < 5; i++) {
            randomQuestionOrder.add(i);
        }
        Collections.shuffle(randomQuestionOrder);
        ArrayList<String> randomQuestionOrder1 = new ArrayList<String>();
        for(int i = 0; i < 5; i++) {
            int j =  randomQuestionOrder.get(i);
            String questionType = "";
            switch (j){
                case 0:{
                    questionType = "height";
                } break;
                case 1:{
                    questionType = "weight";
                } break;
                case 2:{
                    questionType = "type";
                } break;
                case 3:{
                    questionType = "generation";
                } break;
                case 4:{
                    questionType = "speed";
                } break;
            }
            randomQuestionOrder1.add(questionType);
        }

        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question(randomQuestionOrder1.get(0), MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(randomPokemonOrder.get(0))));
        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question(randomQuestionOrder1.get(1), MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(randomPokemonOrder.get(1))));
        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question(randomQuestionOrder1.get(2), MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(randomPokemonOrder.get(2))));
        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question(randomQuestionOrder1.get(3), MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(randomPokemonOrder.get(3))));
        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question(randomQuestionOrder1.get(4), MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(randomPokemonOrder.get(4))));
    }

}
