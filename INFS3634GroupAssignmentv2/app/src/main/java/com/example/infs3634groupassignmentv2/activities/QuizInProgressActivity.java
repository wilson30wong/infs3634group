package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.model.Gym;
import com.example.infs3634groupassignmentv2.model.Quiz;

public class QuizInProgressActivity extends AppCompatActivity {

    TextView quizHeader;
    TextView quizHeader1;
    Button quizOptionA;
    Button quizOptionB;
    Button quizOptionC;
    Button quizOptionD;
    TextView correctStatus;
    ConstraintLayout nextQuestion;
    TextView nextQuestion1;
    int selectedQuiz;
    int selectedGym;
    int quizProgress = 0;
    int correctAnswers = 0;
    public static boolean progress;
    Gym gym;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_in_progress);

        quizHeader = findViewById(R.id.quizHeader);
        quizHeader1 = findViewById(R.id.quizHeader1);
        quizOptionA = findViewById(R.id.quizOptionA);
        quizOptionB = findViewById(R.id.quizOptionB);
        quizOptionC = findViewById(R.id.quizOptionC);
        quizOptionD = findViewById(R.id.quizOptionD);
        correctStatus = findViewById(R.id.correctStatus);
        nextQuestion = findViewById(R.id.nextQuestion);
        nextQuestion1 = nextQuestion.findViewById(R.id.nextQuestion1);

        correctStatus.setVisibility(View.INVISIBLE);
        nextQuestion.setVisibility(View.GONE);

        Intent intent = getIntent();
        selectedQuiz = intent.getIntExtra("selectedQuiz", -1);
        selectedGym = intent.getIntExtra("selectedGym", -1);
        gym = MainActivity.profile.getGame().getGymArrayList().get(selectedGym);
        quiz = MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz);

        System.out.println(selectedGym + "       " + selectedQuiz);
        System.out.println(MainActivity.profile.getGame().getGameProgress() + "           " + MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getGymProgress());

        populatePage();

        if(MainActivity.profile.getGame().getGameProgress() == selectedGym){
            if(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getGymProgress() == selectedQuiz){
                progress = true;
            }
        }

        for(int i = 0; i < 5; i++){
            System.out.println(quiz.getQuestionArrayList().get(i).getAnswerCode());
        }

        nextQuestion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(nextQuestion1.getText().equals("Finish the Quiz")){
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    if(correctAnswers == 5 & progress == true){
                        int newProgress = MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getGymProgress() + 1;
                        MainActivity.profile.getGame().getGymArrayList().get(selectedGym).setGymProgress(newProgress);
                        if(newProgress == 5){
                            int newProgress1 = MainActivity.profile.getGame().getGameProgress() + 1;
                            MainActivity.profile.getGame().setGameProgress(newProgress1);
                        }
                    }
                    startActivity(intent1);
                } else{
                    quizProgress++;
                    quizOptionA.setEnabled(true);
                    quizOptionB.setEnabled(true);
                    quizOptionC.setEnabled(true);
                    quizOptionD.setEnabled(true);
                    correctStatus.setVisibility(View.INVISIBLE);
                    nextQuestion.setVisibility(View.GONE);
                    populatePage();
                }
            }

        });
    }

    private void populatePage(){
        String quizHeaderText = gym.getName() + "\nStage " + selectedQuiz + 1;
        quizHeader.setText(quizHeaderText);
        String quizHeader1Text = "Question " + (quizProgress + 1) + "\n" + quiz.getQuestionArrayList().get(quizProgress).getQuestion();
        quizHeader1.setText(quizHeader1Text);
        String answerCode = quiz.getQuestionArrayList().get(quizProgress).getAnswerCode();
        String quizOptionAText = "";
        String quizOptionBText = "";
        String quizOptionCText = "";
        String quizOptionDText = "";
        switch (answerCode){
            case "A":{
                quizOptionAText = "A. " + quiz.getQuestionArrayList().get(quizProgress).getAnswer();
                quizOptionBText = "B. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(0);
                quizOptionCText = "C. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(1);
                quizOptionDText = "D. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(2);
            } break;
            case "B":{
                quizOptionAText = "A. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(0);
                quizOptionBText = "B. " + quiz.getQuestionArrayList().get(quizProgress).getAnswer();
                quizOptionCText = "C. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(1);
                quizOptionDText = "D. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(2);
            } break;
            case "C":{
                quizOptionAText = "A. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(0);
                quizOptionBText = "B. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(1);
                quizOptionCText = "C. " + quiz.getQuestionArrayList().get(quizProgress).getAnswer();
                quizOptionDText = "D. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(2);
            } break;
            case "D":{
                quizOptionAText = "A. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(0);
                quizOptionBText = "B. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(1);
                quizOptionCText = "C. " + quiz.getQuestionArrayList().get(quizProgress).getWrongAnswers().get(2);
                quizOptionDText = "D. " + quiz.getQuestionArrayList().get(quizProgress).getAnswer();
            } break;
        }
        quizOptionA.setText(quizOptionAText);
        quizOptionB.setText(quizOptionBText);
        quizOptionC.setText(quizOptionCText);
        quizOptionD.setText(quizOptionDText);

        quizOptionA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correctStatus.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
                if(quiz.getQuestionArrayList().get(quizProgress).getAnswerCode().equals("A")){
                    correctStatus.setText("You answered correcty!");
                    correctAnswers++;
                } else{
                    correctStatus.setText("You answered incorrectly!" + "\nThe answer was " +
                            quiz.getQuestionArrayList().get(quizProgress).getAnswerCode() + "!");
                }
                quizOptionA.setEnabled(false);
                quizOptionB.setEnabled(false);
                quizOptionC.setEnabled(false);
                quizOptionD.setEnabled(false);
                if(quizProgress == 4){
                    nextQuestion1.setText("Finish the Quiz");
                }
            }
        });

        quizOptionB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correctStatus.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
                if(quiz.getQuestionArrayList().get(quizProgress).getAnswerCode().equals("B")){
                    correctStatus.setText("You answered correcty!");
                    correctAnswers++;
                } else{
                    correctStatus.setText("You answered incorrectly!" + "\nThe answer was " +
                            quiz.getQuestionArrayList().get(quizProgress).getAnswerCode() + "!");
                }
                quizOptionA.setEnabled(false);
                quizOptionB.setEnabled(false);
                quizOptionC.setEnabled(false);
                quizOptionD.setEnabled(false);
                if(quizProgress == 4){
                    nextQuestion1.setText("Finish the Quiz");
                }
            }
        });

        quizOptionC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correctStatus.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
                if(quiz.getQuestionArrayList().get(quizProgress).getAnswerCode().equals("C")){
                    correctStatus.setText("You answered correcty!");
                    correctAnswers++;
                } else{
                    correctStatus.setText("You answered incorrectly!" + "\nThe answer was " +
                            quiz.getQuestionArrayList().get(quizProgress).getAnswerCode() + "!");
                }
                quizOptionA.setEnabled(false);
                quizOptionB.setEnabled(false);
                quizOptionC.setEnabled(false);
                quizOptionD.setEnabled(false);
                if(quizProgress == 4){
                    nextQuestion1.setText("Finish the Quiz");
                }
            }
        });

        quizOptionD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correctStatus.setVisibility(View.VISIBLE);
                nextQuestion.setVisibility(View.VISIBLE);
                if(quiz.getQuestionArrayList().get(quizProgress).getAnswerCode().equals("D")){
                    correctStatus.setText("You answered correcty!");
                    correctAnswers++;
                } else{
                    correctStatus.setText("You answered incorrectly!" + "\nThe answer was " +
                            quiz.getQuestionArrayList().get(quizProgress).getAnswerCode() + "!");
                }
                quizOptionA.setEnabled(false);
                quizOptionB.setEnabled(false);
                quizOptionC.setEnabled(false);
                quizOptionD.setEnabled(false);
                if(quizProgress == 4){
                    nextQuestion1.setText("Finish the Quiz");

                }
            }
        });
    }

}
