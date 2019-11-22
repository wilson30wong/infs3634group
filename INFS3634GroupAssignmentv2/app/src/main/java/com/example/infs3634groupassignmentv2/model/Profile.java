package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.api.Genera;
import com.example.infs3634groupassignmentv2.api.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {

    private UUID uuid;
    private Game game;
    private ArrayList<FlashCard> flashCardArrayList;
    private int notesCreated;

    public Profile(){
        this.uuid = java.util.UUID.randomUUID();
        this.game = new Game();
        this.flashCardArrayList = new ArrayList<FlashCard>();
        flashCardArrayList.add(new FlashCard());
        this.notesCreated = 0;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<FlashCard> getFlashCardArrayList() {
        return flashCardArrayList;
    }

    public void setFlashCardArrayList(ArrayList<FlashCard> flashCardArrayList) {
        this.flashCardArrayList = flashCardArrayList;
    }

    public int getNotesCreated() {
        return notesCreated;
    }

    public void setNotesCreated(int notesCreated) {
        this.notesCreated = notesCreated;
    }

    public ArrayList<FlashCard> findFlashCards(ArrayList<FlashCard> flashCardsInput, String search) {
        ArrayList<FlashCard> flashCardOutput = new ArrayList<FlashCard>();
        flashCardOutput.add(this.flashCardArrayList.get(0));
        for(FlashCard flashCard : flashCardsInput) {
            if(flashCard.getPokemon().getName().equals(search)) {
                flashCardOutput.add(flashCard);
            }
        }
        return flashCardOutput;
    }



}
