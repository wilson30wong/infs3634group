package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;

public class Genera implements Serializable {

    private String genus;
    private Language language;

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
