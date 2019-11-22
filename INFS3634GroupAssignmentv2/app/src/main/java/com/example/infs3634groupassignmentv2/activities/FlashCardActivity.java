package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.api.Pokemon;
import com.example.infs3634groupassignmentv2.model.FlashCard;

public class FlashCardActivity extends AppCompatActivity {

    ConstraintLayout flashCardContainer;
    EditText flashCardTitle1;
    EditText flashCardBody1;
    ImageButton saveButton;
    Pokemon pokemonObject;
    int id;
    FlashCard flashCardObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        flashCardContainer = findViewById(R.id.flashCardContainer);
        flashCardTitle1 = flashCardContainer.findViewById(R.id.flashCardTitle1);
        flashCardBody1 = flashCardContainer.findViewById(R.id.flashCardBody1);
        saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        pokemonObject = (Pokemon) intent.getSerializableExtra("pokemonObject");
        System.out.println(pokemonObject.getName());

        for(int i = 0; i < MainActivity.profile.getFlashCardArrayList().size(); i++){
            if(id == MainActivity.profile.getFlashCardArrayList().get(i).getId()){
                flashCardObject = MainActivity.profile.getFlashCardArrayList().get(i);
                flashCardTitle1.setText(flashCardObject.getTitle());
                flashCardBody1.setText(flashCardObject.getBody());
                saveButton.setImageResource(R.drawable.ic_save_black_24dp);
            }
        }

        flashCardTitle1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().
                            getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flashCardTitle1.getText().toString().trim().length() == 0 && id > 0){
                    Toast.makeText(getApplicationContext(), "Flash Cards Require Titles", Toast.LENGTH_LONG).show();
                } else {
                    MainActivity.profile.getFlashCardArrayList().remove(flashCardObject);
                    flashCardObject = new FlashCard(id, flashCardTitle1.getText().toString(), flashCardBody1.getText().toString(),pokemonObject);;
                    MainActivity.profile.getFlashCardArrayList().add(flashCardObject);
                    Toast.makeText(getApplicationContext(), "Flash Card Has Been Saved", Toast.LENGTH_SHORT).show();
                    System.out.println(MainActivity.profile.findFlashCards(MainActivity.profile.getFlashCardArrayList(),pokemonObject.getName()).size());
                    saveButton.setImageResource(R.drawable.ic_save_black_24dp);
                }
//                else{
//                    flashCardObject = new FlashCard(id, flashCardTitle1.getText().toString(), flashCardBody1.getText().toString(),pokemonObject);
//                    MainActivity.profile.getFlashCardArrayList().add(flashCardObject);
//                }
            }
        });

    }

}
