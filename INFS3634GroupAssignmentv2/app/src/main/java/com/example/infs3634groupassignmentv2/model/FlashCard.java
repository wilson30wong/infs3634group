package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.api.Pokemon;

public class FlashCard {

    private int id;
    private String title;
    private String body;
    private Pokemon pokemon;

    public FlashCard(){
        this.pokemon = new Pokemon();
    }

    public FlashCard(int id, String title, String body, Pokemon pokemon){
        this.id = id;
        this.title = title;
        this.body = body;
        this.pokemon = pokemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

}
