package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.api.Pokemon;

import java.util.ArrayList;

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
