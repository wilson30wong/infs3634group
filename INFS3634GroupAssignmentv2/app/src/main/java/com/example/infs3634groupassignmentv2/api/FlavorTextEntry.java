package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;

public class FlavorTextEntry implements Serializable {

    private String flavor_text;
    private Language language;

    public String getFlavor_text() {
        return flavor_text;
    }

    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
