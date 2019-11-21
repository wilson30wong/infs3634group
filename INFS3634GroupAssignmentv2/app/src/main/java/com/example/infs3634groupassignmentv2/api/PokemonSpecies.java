package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;
import java.util.List;

public class PokemonSpecies implements Serializable {

    private Habitat habitat;
    private Shape shape;
    private Generation generation;
    private List<FlavorTextEntry> flavor_text_entries;
    private List<Genera> genera;
    private String name;

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public List<FlavorTextEntry> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(List<FlavorTextEntry> flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public List<Genera> getGenera() {
        return genera;
    }

    public void setGenera(List<Genera> genera) {
        this.genera = genera;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlavorTextEntry findFlavorTextEntry(List<FlavorTextEntry> flavorTextEntries, String languageName) {
        for(FlavorTextEntry flavorTextEntry : flavorTextEntries) {
            if(flavorTextEntry.getLanguage().getName().equals(languageName)) {
                return flavorTextEntry;
            }
        }
        return null;
    }

    public Genera findGenus(List<Genera> genera, String languageName) {
        for(Genera genus : genera) {
            if(genus.getLanguage().getName().equals(languageName)) {
                return genus;
            }
        }
        return null;
    }

}
