package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;

public class PokemonAbility implements Serializable {

    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

}
