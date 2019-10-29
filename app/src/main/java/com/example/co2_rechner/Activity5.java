package com.example.co2_rechner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5 extends AppCompatActivity {
    TextView textView_baeume;
    TextView textView_baumAnzahl;
    Double ergebnisAbsolut;
    String baumMenge;

    //HIlfe
    double baumHilfe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        getSupportActionBar().hide();



        Intent intent = getIntent();

            ergebnisAbsolut = Double.valueOf(intent.getStringExtra("ergebnisAbsolut"));

            textView_baeume = findViewById(R.id.textView_trees);
            textView_baumAnzahl=findViewById(R.id.textView_BaumAnzahl);
            
            textView_baeume.setText(this.anzahlTree());
            int baumRunden = (int) baumHilfe + 1;
            if(baumRunden > 1) {

                textView_baumAnzahl.setText("" + getText(R.string.label_textview_baumAnzahlMehrzahl1) + baumRunden + getText(R.string.label_textview_baumAnzahlMehrzahl2));
            }else{
                textView_baumAnzahl.setText("" + getText(R.string.label_textview_baumAnzahlEinzahl1) + baumRunden + getText(R.string.label_textview_baumAnzahlEinzahl2));
            }



    }


        protected String anzahlTree() {
             baumHilfe = ergebnisAbsolut / 10000;
            baumMenge = "";

            for (int count = 0; count < baumHilfe; count++) {
                baumMenge = baumMenge + "\uD83C\uDF33";
            }
            return baumMenge;

        }



    }




