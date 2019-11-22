package com.example.infs3634groupassignmentv2.async;

import com.example.infs3634groupassignmentv2.api.Pokemon;

import java.util.List;

public interface AsyncTaskGetDelegate {
    void handleTaskGetResult(List<Pokemon> pokemonList);
}
