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
    EditText etName ,etAmount,  etFrequency ;
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
        etName = (EditText) findViewById(R.id.med_name);
        etAmount =(EditText) findViewById(R.id.med_amount);
        etFrequency = (EditText) findViewById(R.id.med_freqency);
        save = (Button)findViewById(R.id.button_save);
        back = (Button)findViewById(R.id.button_back);

        // Speicherobject
        sharedPreferences = getSharedPreferences(MYPEREFERENCES, Context.MODE_PRIVATE);

        // setOnClickListener(): wenn auf dem Button gecklickt welsche Erreignise wird ausgeführt
        save.setOnClickListener(View->onSaveButton());
        back.setOnClickListener(View->onBackButton());
    }

    protected void onSaveButton(){
        // to do: daten speichen erstmal probieren mit sharedpereference und nicht SQlite
        // text aus Edittexte Elemnte empfangen und speichern in  lokalen Variablen


        String name = etName.getText().toString();
        String amount = etAmount.getText().toString();
        String frequency = etFrequency.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //  In editor Objecte Werte gespeichert
        editor.putString(NAME, name);
        editor.putString(AMOUNT, amount);
        editor.putString(FREQUENCY , frequency);
        //editor.putInt(AMOUNT, Integer.parseInt(amount));
        //editor.putInt(FREQUENCY, Integer.parseInt(frequency));
        editor.commit(); // gespeichert
        // Zur Veranschalichung : Nahcrict wird gezeigt wenn on save Button gecklickt wird.
        Toast.makeText(AddActivity.this, "Danke , Daten werden in overwiew gespeichert ", Toast.LENGTH_SHORT).show();



    }
    protected void  onBackButton(){
        // to do: back to the startpage
        Intent intentToStartPage = new Intent(this, MainActivity.class);
        startActivity(intentToStartPage);
    }



}
