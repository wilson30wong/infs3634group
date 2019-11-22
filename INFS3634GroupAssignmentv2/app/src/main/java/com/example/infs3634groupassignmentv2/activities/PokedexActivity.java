package com.example.infs3634groupassignmentv2.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class PokedexActivity extends AppCompatActivity {

    EditText searchBar;
    ImageButton searchButton;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    Pokemon selectedPokemonObject;
    ProgressBar loadingCircle1;
    TextView searchLoadingText;
    int gymCounter = 0;
    int quizCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);
        loadingCircle1 = findViewById(R.id.loadingCircle1);
        searchLoadingText = findViewById(R.id.searchLoadingText);

        loadingCircle1.setVisibility(View.GONE);
        searchLoadingText.setVisibility(View.GONE);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        recyclerView = findViewById(R.id.rv_pokedexPokemon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

//        if(MainActivity.profile.getPokemonArrayList().size() == 0){
//            for(int i = 0; i < 807; i++){
//                Pokemon pokemonObject = MainActivity.pokemonArrayList.get(i);
//                PokemonSpecies pokemonSpeciesObject = MainActivity.pokemonSpeciesArrayList.get(i);
//                pokemonObject.setSpecies(pokemonSpeciesObject);
//                MainActivity.profile.getPokemonArrayList().add(pokemonObject);
//            }
//        }

//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().
//                        getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
//                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//                String searchQuery = searchBar.getText().toString().toLowerCase().trim();
//                System.out.println(searchQuery + "is the search");
//                Pokemon pokemonObject = MainActivity.profile.findPokemon(MainActivity.profile.getPokemonArrayList(), searchQuery);
//                ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
//                PokemonAdapter pokemonAdapter = new PokemonAdapter();
//                pokemonArrayList.add(pokemonObject);
//                pokemonAdapter.setData(pokemonArrayList);
//                recyclerView.setAdapter(pokemonAdapter);
//            }
//        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().
                        getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                loadingCircle1.setVisibility(View.VISIBLE);
                searchLoadingText.setVisibility(View.VISIBLE);

                String searchQuery = searchBar.getText().toString().toLowerCase().trim();
                System.out.println(searchQuery + "is the search");

                String url = "https://pokeapi.co/api/v2/pokemon/" + searchQuery + "/";
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
//                        selectedPokemonObject = gson.fromJson(response, Pokemon.class);
                        final Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);

                        String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonObject.getName() + "/";
                        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                Gson gson = new Gson();
                                PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
                                pokemonObject.setSpecies(pokemonSpecies);
                                ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                                pokemonArrayList.add(pokemonObject);
                                pokemonAdapter.setData(pokemonArrayList);
                                loadingCircle1.setVisibility(View.GONE);
                                searchLoadingText.setVisibility(View.GONE);
                                recyclerView.setAdapter(pokemonAdapter);
                            }
                        };
                        Response.ErrorListener errorListener1 = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                Pokemon errorPokemonObject = new Pokemon();
                                ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                                pokemonArrayList.add(errorPokemonObject);
                                pokemonAdapter.setData(pokemonArrayList);
                                loadingCircle1.setVisibility(View.GONE);
                                searchLoadingText.setVisibility(View.GONE);
                                recyclerView.setAdapter(pokemonAdapter);
                            }
                        };
                        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener1);
                        requestQueue.add(stringRequest1);
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        PokemonAdapter pokemonAdapter = new PokemonAdapter();
                        Pokemon errorPokemonObject = new Pokemon();
                        ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                        pokemonArrayList.add(errorPokemonObject);
                        pokemonAdapter.setData(pokemonArrayList);
                        loadingCircle1.setVisibility(View.GONE);
                        searchLoadingText.setVisibility(View.GONE);
                        recyclerView.setAdapter(pokemonAdapter);
                    }
                };
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
                requestQueue.add(stringRequest);

//                String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + searchQuery + "/";
//                Response.Listener<String> responseListener1 = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        PokemonAdapter pokemonAdapter = new PokemonAdapter();
//                        Gson gson = new Gson();
//                        PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
//                        selectedPokemonObject.setSpecies(pokemonSpecies);
//                        ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
//                        pokemonArrayList.add(selectedPokemonObject);
//                        pokemonAdapter.setData(pokemonArrayList);
//                        recyclerView.setAdapter(pokemonAdapter);
//                    }
//                };
//                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener);
//                requestQueue.add(stringRequest1);
            }
        });

        searchBar.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().
                            getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    loadingCircle1.setVisibility(View.VISIBLE);
                    searchLoadingText.setVisibility(View.VISIBLE);

                    String searchQuery = searchBar.getText().toString().toLowerCase().trim();
                    System.out.println(searchQuery + "is the search");

                    String url = "https://pokeapi.co/api/v2/pokemon/" + searchQuery + "/";
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
//                        selectedPokemonObject = gson.fromJson(response, Pokemon.class);
                            final Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);

                            String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonObject.getName() + "/";
                            Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                    Gson gson = new Gson();
                                    PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
                                    pokemonObject.setSpecies(pokemonSpecies);
                                    ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                                    pokemonArrayList.add(pokemonObject);
                                    pokemonAdapter.setData(pokemonArrayList);
                                    loadingCircle1.setVisibility(View.GONE);
                                    searchLoadingText.setVisibility(View.GONE);
                                    recyclerView.setAdapter(pokemonAdapter);
                                }
                            };
                            Response.ErrorListener errorListener1 = new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                    Pokemon errorPokemonObject = new Pokemon();
                                    ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                                    pokemonArrayList.add(errorPokemonObject);
                                    pokemonAdapter.setData(pokemonArrayList);
                                    loadingCircle1.setVisibility(View.GONE);
                                    searchLoadingText.setVisibility(View.GONE);
                                    recyclerView.setAdapter(pokemonAdapter);
                                }
                            };
                            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener1);
                            requestQueue.add(stringRequest1);
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            PokemonAdapter pokemonAdapter = new PokemonAdapter();
                            Pokemon errorPokemonObject = new Pokemon();
                            ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
                            pokemonArrayList.add(errorPokemonObject);
                            pokemonAdapter.setData(pokemonArrayList);
                            loadingCircle1.setVisibility(View.GONE);
                            searchLoadingText.setVisibility(View.GONE);
                            recyclerView.setAdapter(pokemonAdapter);
                        }
                    };
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
                    requestQueue.add(stringRequest);
                    return true;
                }
                return false;
            }
        });

    }
}
