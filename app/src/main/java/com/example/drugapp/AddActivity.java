package com.example.drugapp;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText medName ,medAmount,  medFrequency ;
    Button save , back ;

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
        back = (Button)findViewById(R.id.button_back);

        save.setOnClickListener(View->onSaveButton());
        back.setOnClickListener(View->onBackButton());
    }

    protected void onSaveButton(){
        // to do daten speichen
    }
    protected void  onBackButton(){
        // to do: back to the startpage
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



}
