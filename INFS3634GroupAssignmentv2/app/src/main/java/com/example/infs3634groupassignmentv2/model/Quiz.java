package com.example.infs3634groupassignmentv2.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Quiz {

    private ArrayList<Pokemon> pokemonArrayList;
    private ArrayList<Question> questionArrayList;

    public Quiz(){
        this.pokemonArrayList = new ArrayList<Pokemon>();
        this.questionArrayList= new ArrayList<Question>();
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void setQuestionArrayList(ArrayList<Question> questionArrayList) {
        this.questionArrayList = questionArrayList;
    }

    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }

    public void setPokemonArrayList(ArrayList<Pokemon> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }




}
