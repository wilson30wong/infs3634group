package com.example.infs3634groupassignmentv2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.api.PokemonSpecies;
import com.example.infs3634groupassignmentv2.api.PokemonSprites;
import com.example.infs3634groupassignmentv2.api.PokemonType;
import com.example.infs3634groupassignmentv2.api.Shape;
import com.example.infs3634groupassignmentv2.api.Type;

@Database(entities = {Pokemon.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PokemonDao pokemonDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "appDb").allowMainThreadQueries().build();
        }
        return instance;
    }
}
