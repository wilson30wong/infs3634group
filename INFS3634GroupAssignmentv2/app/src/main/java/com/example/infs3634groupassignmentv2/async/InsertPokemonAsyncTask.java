package com.example.infs3634groupassignmentv2.async;

import android.os.AsyncTask;

import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.database.AppDatabase;

import java.util.Arrays;

public class InsertPokemonAsyncTask extends AsyncTask<Pokemon, Integer, String> {

    private AsyncTaskInsertDelegate delegate;

    private AppDatabase database;

    public void setDelegate(AsyncTaskInsertDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Pokemon... pokemon) {
        for(int i = 0; i < pokemon.length; i++){
            pokemon[i].setId(i);
        }
        database.pokemonDao().insertAll(Arrays.asList(pokemon));
        return "This value will be passed to onPostExecute as the result parameter.";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.handleTaskResult(result);
    }
}

