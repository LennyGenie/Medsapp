package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    Button add ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    protected void initViews(){
        tv = (TextView) findViewById(R.id.textView_hello);
        add = (Button) findViewById(R.id.button_add);
        add.setOnClickListener(View->onAddButton());
    }

    protected void onAddButton(){
        Intent intentToAddActivity = new Intent(this,AddActivity.class );
        startActivity(intentToAddActivity);
    }
}