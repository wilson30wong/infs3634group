package com.example.infs3634groupassignmentv2.api;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Pokemon implements Serializable {

    private int id;
    @PrimaryKey
    @NonNull
    private String name;
    private String url;
    @Ignore
    private PokemonSprites sprites;
    private int height = 0;
    private int weight;
    @Ignore
    private List<PokemonType> types;
    @Ignore
    private List<PokemonAbility> abilities;
    @Ignore
    private List<PokemonStat> stats;
    @Ignore
    private PokemonSpecies species;

    public Pokemon(){
        this.name = "No Pokemon Found";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PokemonSprites getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public List<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStat> stats) {
        this.stats = stats;
    }

    public PokemonSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PokemonSpecies species) {
        this.species = species;
    }

    public boolean checkIfHeightExists(){
        if(this.height == 0){
            return false;
        }
        return true;
    }

    public boolean checkIfSpeciesExists(){
        if(this.species == null){
            return false;
        }
        return true;
    }

}
