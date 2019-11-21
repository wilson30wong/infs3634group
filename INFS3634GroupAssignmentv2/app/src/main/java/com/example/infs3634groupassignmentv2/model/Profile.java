package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.api.Genera;
import com.example.infs3634groupassignmentv2.api.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {

    private UUID uuid;
    private Game game;
    private ArrayList<FlashCard> flashCardArrayList;
    private ArrayList<Pokemon> pokemonArrayList;

    public Profile(){
        this.uuid = java.util.UUID.randomUUID();
        this.game = new Game();
        this.flashCardArrayList = new ArrayList<FlashCard>();
        this.flashCardArrayList.add(new FlashCard());
        this.pokemonArrayList = new ArrayList<Pokemon>();
    }

    //method to create a new game

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<FlashCard> getFlashCardArrayList() {
        return flashCardArrayList;
    }

    public void setFlashCardArrayList(ArrayList<FlashCard> flashCardArrayList) {
        this.flashCardArrayList = flashCardArrayList;
    }

    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }

    public void setPokemonArrayList(ArrayList<Pokemon> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }

    public Pokemon findPokemon(List<Pokemon> pokemonList, String search) {
        int searchNumber = Integer.parseInt(search);
        for(Pokemon pokemon : pokemonList) {
            if(pokemon.getName().equals(search)) {
                return pokemon;
            } else if(pokemon.getId() == searchNumber) {
                return pokemon;
            }
        }
        return null;
    }

}
