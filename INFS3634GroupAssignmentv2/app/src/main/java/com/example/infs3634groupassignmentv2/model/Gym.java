package com.example.infs3634groupassignmentv2.model;

import java.util.ArrayList;

public class Gym {

    private String name;
    private ArrayList<Quiz> quizArrayList;
    private int gymProgress;

    public Gym(String name){
        this.name = name;
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
