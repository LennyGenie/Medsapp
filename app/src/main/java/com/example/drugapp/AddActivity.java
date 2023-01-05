package com.example.drugapp;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    // Konstanen Keys für putExtra() fkt
    public static final String MYPEREFERENCES= "myprefs";
    public static final String NAME = "med_name";
    public static final String AMOUNT = "med_amount";
    public static final String FREQUENCY = "med_frequency";

    // Edittexte und Button Elemente und speicherobject
    EditText medName ,medAmount,  medFrequency ;
    Button save , back ;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
    }

    protected void initViews(){
        // Die Verbindung zichen Jave und xml
        medName = (EditText) findViewById(R.id.med_name);
        medAmount =(EditText) findViewById(R.id.med_amount);
        medFrequency = (EditText) findViewById(R.id.med_freqency);
        save = (Button)findViewById(R.id.button_save);
        back = (Button)findViewById(R.id.button_back);

        // Speicherobject
        sharedPreferences = getSharedPreferences(MYPEREFERENCES, Context.MODE_PRIVATE);

        // setOnClickListener(): wenn auf dem Button gecklickt welsche Erreignise wird ausgeführt
        save.setOnClickListener(View->onSaveButton());
        back.setOnClickListener(View->onBackButton());
    }

    protected void onSaveButton(){
        // to do daten speichen erstmal probieren mit sharedpereference und nicht sqlite
        // text aus edittexte Elemnte empfangen und speichern in Variablen
        String name = medName.getText().toString();
        String amount = medAmount.getText().toString();
        String frequency = medFrequency.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME, name);
        editor.putString(AMOUNT, amount);
        editor.putString(FREQUENCY , frequency);
        editor.commit();
        Toast.makeText(AddActivity.this, "Danke , Daten werden gespeichert ", Toast.LENGTH_SHORT).show();



    }
    protected void  onBackButton(){
        // to do: back to the startpage
        Intent intentToStartPage = new Intent(this, MainActivity.class);
        startActivity(intentToStartPage);
    }



}
