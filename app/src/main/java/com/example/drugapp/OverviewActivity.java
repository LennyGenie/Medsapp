package com.example.drugapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class OverviewActivity extends AppCompatActivity {
    // keys als Konstanten initialiseren
    public static final String MYPEREFERENCES = "myprefs";
    public static final String NAME = "med_name";
    public static final String AMOUNT = "med_amount";
    public static final String FREQUENCY = "med_frequency";

    // TextViews initialiseren
    TextView tvIndex , tvName , tvAmount ,tvFrequency;

    // shredpereference object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        initViews();
    }

    protected void initViews(){
        // Verbindung zwichen Java und xml
        tvIndex =(TextView)findViewById(R.id.row_number);
        tvName=(TextView)findViewById(R.id.med_name) ;
        tvAmount = (TextView)findViewById(R.id.med_amount);
        tvFrequency = (TextView)findViewById(R.id.med_freqency);

        // sharedpereference und scharedpereference editor
        sharedPreferences = getSharedPreferences(MYPEREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Daten empfangen aus dem scharepereference und speichern in lokalen Variablen
        //int index = 1;

        // data object:

        String name = sharedPreferences.getString(NAME, "");
        String  amount = sharedPreferences.getString(AMOUNT, "");
        String frequency = sharedPreferences.getString(FREQUENCY, "");

        // Daten nach dem Ablesen in Textviews anzeigen
        if(name !=null && amount != "" && frequency !="" ){
           // tvIndex.setText(index);
            tvName.setText(name);
            tvAmount.setText(amount);
            tvFrequency.setText(frequency);
        }

    }
}
