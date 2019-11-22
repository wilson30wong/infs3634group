package com.example.infs3634groupassignmentv2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.api.PokemonList;
import com.example.infs3634groupassignmentv2.api.PokemonSpecies;
import com.example.infs3634groupassignmentv2.async.AsyncTaskGetDelegate;
import com.example.infs3634groupassignmentv2.async.AsyncTaskInsertDelegate;
import com.example.infs3634groupassignmentv2.async.GetPokemonAsyncTask;
import com.example.infs3634groupassignmentv2.async.InsertPokemonAsyncTask;
import com.example.infs3634groupassignmentv2.database.AppDatabase;
import com.example.infs3634groupassignmentv2.fragments.GameFragment;
import com.example.infs3634groupassignmentv2.fragments.HomeFragment;
import com.example.infs3634groupassignmentv2.fragments.PokedexFragment;
import com.example.infs3634groupassignmentv2.model.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskInsertDelegate, AsyncTaskGetDelegate {

    public static Profile profile = new Profile();
    BottomNavigationView bottomNavBar;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    AppDatabase db;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, new HomeFragment());
        fragmentTransaction.commit();

        bottomNavBar = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.HomeMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new HomeFragment()).commit();
                            }
                            break;
                            case R.id.GameMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new GameFragment()).commit();
                            }
                            break;
                            case R.id.PokedexMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new PokedexFragment()).commit();
                            }
                            break;
                        }
                        return true;
                    }
                });

        if (db.pokemonDao().getAll().size() == 0) {
            getPokemon();
        } else if (profile.getGame().getGymArrayList().get(0).getQuizArrayList().get(0).getPokemonArrayList().size() == 0) {
            getRandomPokemon();
        }
    }

    private void getRandomPokemon() {
        GetPokemonAsyncTask getPokemonAsyncTask = new GetPokemonAsyncTask();
        getPokemonAsyncTask.setDatabase(db);
        getPokemonAsyncTask.setDelegate(MainActivity.this);
        getPokemonAsyncTask.execute();
    }

    private void getPokemon() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://pokeapi.co/api/v2/pokemon/?limit=807&offset=0";
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PokemonList pokemonListObject = gson.fromJson(response, PokemonList.class);
                List<Pokemon> pokemonList = pokemonListObject.getResults();
                InsertPokemonAsyncTask insertPokemonAsyncTask = new InsertPokemonAsyncTask();
                insertPokemonAsyncTask.setDatabase(db);
                insertPokemonAsyncTask.setDelegate(MainActivity.this);
                Pokemon[] pokemon = pokemonList.toArray(new Pokemon[pokemonList.size()]);
                insertPokemonAsyncTask.execute(pokemon);
                requestQueue.stop();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    @Override
    public void handleTaskResult(String result) {
        getRandomPokemon();
    }

    @Override
    public void handleTaskGetResult(List<Pokemon> pokemon) {
        if (profile.getGame().getGymArrayList().get(0).getQuizArrayList().get(0).getPokemonArrayList().size() == 0) {
            ArrayList<Integer> randomPokemonIdArrayList = new ArrayList<>(807);
            for (int i = 0; i < 807; i++) {
                int id = i + 1;
                randomPokemonIdArrayList.add(id);
            }
            Collections.shuffle(randomPokemonIdArrayList);
            List<Integer> randomPokemonIdList = randomPokemonIdArrayList.subList(0, 175);
            ArrayList<Integer> randomPokemonIdArrayList1 = new ArrayList<Integer>(randomPokemonIdList);

            int quizCounter = 0;
            int gymCounter = 0;

            for (int i = 0; i < 175; ++i) {
                if (i != 0 && i % 5 == 0 && i != 174) {
                    quizCounter++;
                }
                if (i != 0 && i % 25 == 0 && i != 174) {
                    quizCounter = 0;
                    gymCounter++;
                }

                Pokemon pokemonObject = db.pokemonDao().findPokemonById(randomPokemonIdArrayList1.get(i));
                profile.getGame().getGymArrayList().get(gymCounter).getQuizArrayList().get(quizCounter).getPokemonArrayList().add(pokemonObject);
            }
            Toast.makeText(getApplicationContext(), "App Is Ready For Use", Toast.LENGTH_SHORT).show();
        }
    }

}
