package com.example.infs3634groupassignmentv2.async;

import android.os.AsyncTask;

import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.database.AppDatabase;

import java.util.List;

public class GetPokemonAsyncTask extends AsyncTask<Void, Integer, List<Pokemon>> {

    private AsyncTaskGetDelegate delegate;

    private AppDatabase database;

    public void setDelegate(AsyncTaskGetDelegate delegate) {
        this.delegate = delegate;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    @Override
    protected List<Pokemon> doInBackground(Void... voids) {
        return database.pokemonDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Pokemon> result) {
        delegate.handleTaskGetResult(result);
    }

}
