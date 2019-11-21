package com.example.infs3634groupassignmentv2;

import android.os.Bundle;
import android.view.MenuItem;

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
import com.example.infs3634groupassignmentv2.api.PokemonSpecies;
import com.example.infs3634groupassignmentv2.fragments.GameFragment;
import com.example.infs3634groupassignmentv2.fragments.HomeFragment;
import com.example.infs3634groupassignmentv2.fragments.PokedexFragment;
import com.example.infs3634groupassignmentv2.model.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    public static Profile profile  = new Profile();
    BottomNavigationView bottomNavBar;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    public static ArrayList<Pokemon> pokemonArrayList = new ArrayList<Pokemon>();
    public static ArrayList<PokemonSpecies> pokemonSpeciesArrayList = new ArrayList<PokemonSpecies>();
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, new HomeFragment());
        fragmentTransaction.commit();

        bottomNavBar = (BottomNavigationView) findViewById(R.id.bottomNavBar);
        bottomNavBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        switch(id){
                            case R.id.HomeMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new HomeFragment()).commit();
                            } break;
                            case R.id.GameMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new GameFragment()).commit();
                            } break;
                            case R.id.PokedexMenu: {
                                fragmentManager.beginTransaction().replace(R.id.fragment_slot, new PokedexFragment()).commit();
                            } break;
                        }
                        return true;
                    }
                });

//        getPokemon();

        if(profile.getGame().getGymArrayList().get(0).getQuizArrayList().get(0).getPokemonArrayList().size() == 0){
            getRandomPokemon();
        }

    }

    private void getRandomPokemon(){
        final ArrayList<Integer> randomPokemonIdArrayList = new ArrayList<>(807);
        for (int i = 0; i < 807; i++){
            int id = i + 1;
            randomPokemonIdArrayList.add(id);
        }
        Collections.shuffle(randomPokemonIdArrayList);
        List<Integer> randomPokemonIdList = randomPokemonIdArrayList.subList(0,175);
        ArrayList<Integer> randomPokemonIdArrayList1 = new ArrayList<Integer>(randomPokemonIdList);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);
                pokemonArrayList.add(pokemonObject);
                System.out.println(pokemonObject.getName() + " was added");
            }
        };
        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PokemonSpecies pokemonSpeciesObject = gson.fromJson(response, PokemonSpecies.class);
                pokemonSpeciesArrayList.add(pokemonSpeciesObject);
                System.out.println(pokemonSpeciesObject.getName() + "'s species was added");
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };

        for(int i = 0; i < 175; i++){
            String url = "https://pokeapi.co/api/v2/pokemon/" + randomPokemonIdArrayList1.get(i) + "/";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
            requestQueue.add(stringRequest);
            String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + randomPokemonIdArrayList1.get(i) + "/";
            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener);
            requestQueue.add(stringRequest1);
        }

    }

//    private void getPokemon(){
//        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        Response.Listener<String> responseListener = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                Pokemon pokemonObject = gson.fromJson(response, Pokemon.class);
//                pokemonArrayList.add(pokemonObject);
//                System.out.println(pokemonObject.getName() + " was added");
//            }
//        };
//        Response.Listener<String> responseListener1 = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                PokemonSpecies pokemonSpeciesObject = gson.fromJson(response, PokemonSpecies.class);
//                pokemonSpeciesArrayList.add(pokemonSpeciesObject);
//                System.out.println(pokemonSpeciesObject.getName() + "'s species was added");
//            }
//        };
//        Response.ErrorListener errorListener = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                requestQueue.stop();
//            }
//        };
//        for(int i = 0 ; i < 807; i++){
//            String url = "https://pokeapi.co/api/v2/pokemon/" + (i+1) + "/";
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
//            requestQueue.add(stringRequest);
//            String url1 = "https://pokeapi.co/api/v2/pokemon-species/" + (i+1) + "/";
//            StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, responseListener1, errorListener);
//            requestQueue.add(stringRequest1);
//        }
//    }
}
