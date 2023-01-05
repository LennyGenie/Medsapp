package com.example.drugapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText medName ,medAmount,  medFrequency ;
    Button save ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
    }

    protected void initViews(){
        medName = (EditText) findViewById(R.id.med_name);
        medAmount =(EditText) findViewById(R.id.med_amount);
        medFrequency = (EditText) findViewById(R.id.med_freqency);
        save = (Button)findViewById(R.id.button_save);
        save.setOnClickListener(View->onSaveButton());
    }

    protected void onSaveButton(){
        // daten speichern
    }
}
