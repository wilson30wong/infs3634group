package com.example.infs3634groupassignmentv2.model;

import java.util.ArrayList;

public class Game {

    private ArrayList<Gym> gymArrayList;
    private int gameProgress;

    public Game(){
        gymArrayList = new ArrayList<Gym>();
        this.gameProgress = 0;
        this.gymArrayList.add(new Gym("First Gym"));
        this.gymArrayList.add(new Gym("Second Gym"));
        this.gymArrayList.add(new Gym("Third Gym"));
        this.gymArrayList.add(new Gym("Fourth Gym"));
        this.gymArrayList.add(new Gym("Fifth Gym"));
        this.gymArrayList.add(new Gym("Sixth Gym"));
        this.gymArrayList.add(new Gym("Seventh Gym"));
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
