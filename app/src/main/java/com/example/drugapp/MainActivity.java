package com.example.drugapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    Button add ,overview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    protected void initViews(){
        tv = (TextView) findViewById(R.id.textView_hello);
        add = (Button) findViewById(R.id.button_add);
        overview = (Button)findViewById(R.id.button_Overwiew) ;
        add.setOnClickListener(View->onAddButton());
        overview.setOnClickListener(View->onOverviewButton());

    }

    protected void onAddButton(){
        Intent toAddActivity = new Intent(this,AddActivity.class );
        startActivity(toAddActivity);
    }

    protected void onOverviewButton(){
        Intent toOverviewActivity = new Intent(this,OverviewActivity.class);
        startActivity(toOverviewActivity);
    }
}