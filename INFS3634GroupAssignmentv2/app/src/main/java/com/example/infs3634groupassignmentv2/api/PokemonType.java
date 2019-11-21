package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;

public class PokemonType implements Serializable {

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
