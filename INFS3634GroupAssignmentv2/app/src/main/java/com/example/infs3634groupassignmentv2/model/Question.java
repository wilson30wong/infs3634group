package com.example.infs3634groupassignmentv2.model;

import com.example.infs3634groupassignmentv2.api.Pokemon;

import java.util.ArrayList;
import java.util.Random;

public class Question {

    private String questionType;
    private Pokemon pokemon;
    private String question;
    private String answer;
    private String answerCode;
    private ArrayList<String> wrongAnswers;

    public Question(String questionType, Pokemon pokemon){
        this.questionType = questionType;
        this.pokemon = pokemon;
        String name = pokemon.getName();
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        switch(questionType){
            case "height":{
                this.question = "What is " + name + "'s height?";
                double height = ((double) pokemon.getHeight()) / 10;
                this.answer = Double.toString(height) + "m";
                this.answerCode = getRandomAnswerCode();
                this.wrongAnswers = getWrongHeightAnswers(pokemon);
            } break;
            case "weight":{
                this.question = "What is " + name + "'s weight?";
                double weight = ((double) pokemon.getWeight()) / 10;
                this.answer = Double.toString(weight) + "kg";
                this.answerCode = getRandomAnswerCode();
                this.wrongAnswers = getWrongWeightAnswers(pokemon);
            } break;
            case "type":{
                this.question = "What is one of " + name + "'s types?";
                String answer = pokemon.getTypes().get(getRandom(0, pokemon.getTypes().size() - 1)).getType().getName();
                this.answer = answer.substring(0,1).toUpperCase() + answer.substring(1).toLowerCase();
                this.answerCode = getRandomAnswerCode();
                this.wrongAnswers = getWrongTypeAnswers(pokemon);
            } break;

        }
    }

    private int getRandom(int min, int max){
        int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }

    private String getRandomAnswerCode(){
        String answerCode = "A";
        int random = getRandom(1,4);
        switch(random){
            case 1:{
                answerCode = "A";
            } break;
            case 2:{
                answerCode = "B";
            } break;
            case 3:{
                answerCode = "C";
            } break;
            case 4:{
                answerCode = "D";
            } break;
        }
        return answerCode;
    }

    private ArrayList<String> getWrongHeightAnswers(Pokemon pokemon){
        ArrayList<String> wrongAnswers = new ArrayList<String>();
        for(int i = 0; i < 3; i++){
            int random = getRandom(5, (pokemon.getHeight() + 20));
            if(random == pokemon.getHeight()){
                i--;
                continue;
            }
            double height = ((double) random) / 10;
            String wrongAnswer = Double.toString(height) + "m";
            if (i == 0) {
                wrongAnswers.add(wrongAnswer);
            } else{
                boolean nonDuplicate = true;
                for(int j = 0; j < wrongAnswers.size(); j++){
                    if(wrongAnswer.equals(wrongAnswers.get(j))){
                        i--;
                        nonDuplicate = false;
                        break;
                    } else{
                        nonDuplicate = true;
                    }
                }
                if(nonDuplicate == true){
                    wrongAnswers.add(wrongAnswer);
                }
            }
        }
        return wrongAnswers;
    }

    private ArrayList<String> getWrongWeightAnswers(Pokemon pokemon){
        ArrayList<String> wrongAnswers = new ArrayList<String>();
        for(int i = 0; i < 3; i++){
            int random = getRandom((pokemon.getWeight()/2), (pokemon.getWeight() + (pokemon.getWeight()/2)));
            if(random == pokemon.getWeight()){
                i--;
                continue;
            }
            double weight = ((double) random) / 10;
            String wrongAnswer = Double.toString(weight) + "kg";
            if (i == 0) {
                wrongAnswers.add(wrongAnswer);
            } else{
                boolean nonDuplicate = true;
                for(int j = 0; j < wrongAnswers.size(); j++){
                    if(wrongAnswer.equals(wrongAnswers.get(j))){
                        i--;
                        nonDuplicate = false;
                        break;
                    } else{
                        nonDuplicate = true;
                    }
                }
                if(nonDuplicate == true){
                    wrongAnswers.add(wrongAnswer);
                }
            }
        }
        return wrongAnswers;
    }

    private ArrayList<String> getWrongTypeAnswers(Pokemon pokemon){
        ArrayList<String> wrongAnswers = new ArrayList<String>();

        String wrongAnswer = "";
        String type1 = pokemon.getTypes().get(0).getType().getName();
        String type2 = "";
        if(pokemon.getTypes().size() == 2){
            type2 = pokemon.getTypes().get(1).getType().getName();
        }

        for(int i = 0; i < 3; i++){
            int random = getRandom(1,18);
            switch (random){
                case 1:{
                    wrongAnswer = "normal";
                } break;
                case 2:{
                    wrongAnswer = "fighting";
                } break;
                case 3:{
                    wrongAnswer = "flying";
                } break;
                case 4:{
                    wrongAnswer = "poison";
                } break;
                case 5:{
                    wrongAnswer = "ground";
                } break;
                case 6:{
                    wrongAnswer = "rock";
                } break;
                case 7:{
                    wrongAnswer = "bug";
                } break;
                case 8:{
                    wrongAnswer = "ghost";
                } break;
                case 9:{
                    wrongAnswer = "steel";
                } break;
                case 10:{
                    wrongAnswer = "fire";
                } break;
                case 11:{
                    wrongAnswer = "water";
                } break;
                case 12:{
                    wrongAnswer = "grass";
                } break;
                case 13:{
                    wrongAnswer = "electric";
                } break;
                case 14:{
                    wrongAnswer = "psychic";
                } break;
                case 15:{
                    wrongAnswer = "ice";
                } break;
                case 16:{
                    wrongAnswer = "dragon";
                } break;
                case 17:{
                    wrongAnswer = "dark";
                } break;
                case 18:{
                    wrongAnswer = "fairy";
                } break;
            }

            if(wrongAnswer.equals(type1) || wrongAnswer.equals(type2)){
                i--;
                continue;
            }

            if (i == 0) {
                wrongAnswer = wrongAnswer.substring(0,1).toUpperCase() + wrongAnswer.substring(1).toLowerCase();
                wrongAnswers.add(wrongAnswer);
            } else{
                boolean nonDuplicate = true;
                for(int j = 0; j < wrongAnswers.size(); j++){
                    if(wrongAnswer.equals(wrongAnswers.get(j))){
                        i--;
                        nonDuplicate = false;
                        break;
                    } else{
                        nonDuplicate = true;
                    }
                }
                if(nonDuplicate == true){
                    wrongAnswer = wrongAnswer.substring(0,1).toUpperCase() + wrongAnswer.substring(1).toLowerCase();
                    wrongAnswers.add(wrongAnswer);
                }
            }
        }
        return wrongAnswers;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(ArrayList<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
