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
    ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();


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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().
                        getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                loadingCircle1.setVisibility(View.VISIBLE);
                searchLoadingText.setVisibility(View.VISIBLE);
                pokemonArrayList.clear();

                String searchQuery = searchBar.getText().toString().toLowerCase().trim();
                String url = "https://pokeapi.co/api/v2/pokemon/" + searchQuery + "/";
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);
                        pokemonArrayList.add(pokemonObject);
                        String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonObject.getName() + "/";
                        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                Gson gson = new Gson();
                                PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
                                pokemonArrayList.get(0).setSpecies(pokemonSpecies);
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
                    pokemonArrayList.clear();

                    String searchQuery = searchBar.getText().toString().toLowerCase().trim();
                    String url = "https://pokeapi.co/api/v2/pokemon/" + searchQuery + "/";
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);
                            pokemonArrayList.add(pokemonObject);
                            String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + pokemonObject.getName() + "/";
                            Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    PokemonAdapter pokemonAdapter = new PokemonAdapter();
                                    Gson gson = new Gson();
                                    PokemonSpecies pokemonSpecies = gson.fromJson(response,PokemonSpecies.class);
                                    pokemonArrayList.get(0).setSpecies(pokemonSpecies);
                                    pokemonAdapter.setData(pokemonArrayList);
                                    loadingCircle1.setVisibility(View.GONE);
                                    searchLoadingText.setVisibility(View.GONE);
                                    recyclerView.setAdapter(pokemonAdapter);
                                    pokemonArrayList.clear();
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
                                    pokemonArrayList.clear();
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
                            pokemonArrayList.clear();
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
