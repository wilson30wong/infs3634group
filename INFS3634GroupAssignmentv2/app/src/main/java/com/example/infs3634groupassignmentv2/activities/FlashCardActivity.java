package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.model.FlashCard;

public class FlashCardActivity extends AppCompatActivity {

    EditText flashCardTitle1;
    EditText flashCardBody1;
    Button saveButton;
    int id;
    FlashCard flashCardObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        flashCardTitle1 = findViewById(R.id.flashCardTitle1);
        flashCardBody1 = findViewById(R.id.flashCardBody1);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        for(int i = 0; i < MainActivity.profile.getFlashCardArrayList().size(); i++){
            if(id == MainActivity.profile.getFlashCardArrayList().get(i).getId()){
                flashCardObject = MainActivity.profile.getFlashCardArrayList().get(i);
                flashCardTitle1.setText(flashCardObject.getTitle());
                flashCardBody1.setText(flashCardObject.getBody());
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flashCardTitle1.getText().length() == 0 && id == 0){

                } else if(flashCardTitle1.getText().length() == 0 && id > 0){
                    MainActivity.profile.getFlashCardArrayList().remove(flashCardObject);
                } else if(id > 0){
                    MainActivity.profile.getFlashCardArrayList().remove(flashCardObject);
                    flashCardObject = new FlashCard(id, flashCardTitle1.getText().toString(), flashCardBody1.getText().toString());
                    MainActivity.profile.getFlashCardArrayList().add(flashCardObject);
                } else{
                    flashCardObject = new FlashCard(id, flashCardTitle1.getText().toString(), flashCardBody1.getText().toString());
                    MainActivity.profile.getFlashCardArrayList().add(flashCardObject);
                }
            }
        });

    }

}
