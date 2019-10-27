package com.example.co2_rechner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity {
    TextView textView_baeume;
    Double ergebnisAbsolut;
    String baeume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        getSupportActionBar().hide();


        Intent intent = getIntent();
            ergebnisAbsolut = Double.valueOf(intent.getStringExtra("ergebnisAbsolut"));

            textView_baeume = findViewById(R.id.textView_trees);
            textView_baeume.setText(this.anzahlTree());


    }


        protected String anzahlTree() {
            double baumHilfe = ergebnisAbsolut / 10000;
            baeume = "";

            for (int count = 0; count < baumHilfe; count++) {
                baeume = baeume + "\uD83C\uDF33";
            }
            return baeume;

        }



    }




