package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.adapters.PokemonAdapter;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.api.PokemonSpecies;
import com.google.gson.Gson;

import java.util.ArrayList;

public class QuizSplashscreenActivity extends AppCompatActivity {

    int selectedQuiz;
    int selectedGym;
    ProgressBar loadingCircle;
    ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
    int pendingRequests = 0;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_splashcreen);

        loadingCircle = findViewById(R.id.loadingCircle);
//        loadingCircle.setVisibility(View.GONE);

        Intent intent = getIntent();
        selectedGym = intent.getIntExtra("selectedGym", -1);
        selectedQuiz = intent.getIntExtra("selectedQuiz", -1);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);
                pokemonArrayList.add(pokemonObject);
                System.out.println(pokemonObject.getName() + " has been added");
                pendingRequests--;

                if (pendingRequests == 0) {
                    Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
                            pendingRequests--;
                            System.out.println(pokemonSpecies.getName() + " is the species");

                            for(int i = 0; i < pokemonArrayList.size(); i++){
                                System.out.println(pokemonArrayList.get(i).getName() + " is being checked");
                                if(pokemonSpecies.getName().equals(pokemonArrayList.get(i).getName())){
                                    System.out.println(pokemonArrayList.get(i).getName());
                                    pokemonArrayList.get(i).setSpecies(pokemonSpecies);
                                    System.out.println(pokemonArrayList.get(i).getSpecies().findFlavorTextEntry(pokemonSpecies.getFlavor_text_entries(), "en").getFlavor_text());

                                }
                            }

                            if(pendingRequests == 0){
                                for (int i = 0; i < 5; i++) {
                                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().set(i, pokemonArrayList.get(i));
                                    System.out.println(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getSprites().getFront_default());
//                                    System.out.println(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getSpecies().findFlavorTextEntry(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getSpecies().getFlavor_text_entries(), "en"));
                                }
                                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                                intent.putExtra("selectedGym", selectedGym);
                                intent.putExtra("selectedQuiz", selectedQuiz);
                                startActivity(intent);
                                finish();
                            }
                        }
                    };
                    Response.ErrorListener errorListener1 = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("helooooooooooo");
                        }
                    };
                    for (int i = 0; i < 5; i++) {
//                        String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getId() + "/";
                        String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonArrayList.get(i).getId() + "/";
                        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener1);
                        requestQueue.add(stringRequest1);
                        pendingRequests++;
                    }
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };


        for (int i = 0; i < 5; i++) {
            String url = MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getUrl();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
            requestQueue.add(stringRequest);
            pendingRequests++;
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
//                intent.putExtra("selectedGym", selectedGym);
//                intent.putExtra("selectedQuiz", selectedQuiz);
//                startActivity(intent);
//                finish();
//            }
//        },5000);

    }
}
