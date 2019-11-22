package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.R;

import java.util.ArrayList;

public class Game {

    private ArrayList<Gym> gymArrayList;
    private int gameProgress;

    public Game(){
        gymArrayList = new ArrayList<Gym>();
        this.gameProgress = 0;
        this.gymArrayList.add(new Gym("Pewter Gym", R.drawable.badge1));
        this.gymArrayList.add(new Gym("Violet Gym", R.drawable.badge2));
        this.gymArrayList.add(new Gym("Rustboro Gym", R.drawable.badge3));
        this.gymArrayList.add(new Gym("Oreburgh Gym", R.drawable.badge4));
        this.gymArrayList.add(new Gym("Striaton Gym", R.drawable.badge5));
        this.gymArrayList.add(new Gym("Santalune Gym", R.drawable.badge6));
        this.gymArrayList.add(new Gym("Elite Gym", R.drawable.badge7));
    }

    public ArrayList<Gym> getGymArrayList() {
        return gymArrayList;
    }

    public void setGymArrayList(ArrayList<Gym> gymArrayList) {
        this.gymArrayList = gymArrayList;
    }

    public int getGameProgress() {
        return gameProgress;
    }

    public void setGameProgress(int gameProgress) {
        this.gameProgress = gameProgress;
    }
}
