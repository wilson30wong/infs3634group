package com.example.infs3634groupassignmentv2.model;

import java.util.ArrayList;

public class Gym {

    private String name;
    private int badge;
    private ArrayList<Quiz> quizArrayList;
    private int gymProgress;

    public Gym(String name, int badge){
        this.name = name;
        this.badge = badge;
        this.gymProgress = 0;
        this.quizArrayList = new ArrayList<Quiz>();
        this.quizArrayList.add(new Quiz());
        this.quizArrayList.add(new Quiz());
        this.quizArrayList.add(new Quiz());
        this.quizArrayList.add(new Quiz());
        this.quizArrayList.add(new Quiz());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public ArrayList<Quiz> getQuizArrayList() {
        return quizArrayList;
    }

    public void setQuizArrayList(ArrayList<Quiz> quizArrayList) {
        this.quizArrayList = quizArrayList;
    }

    public int getGymProgress() {
        return gymProgress;
    }

    public void setGymProgress(int gymProgress) {
        this.gymProgress = gymProgress;
    }
}
