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
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("height", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("weight", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(1)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(2)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(3)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(4)));
                } else {
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("height", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("weight", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(1)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(2)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(3)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type", MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(4)));
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
}
