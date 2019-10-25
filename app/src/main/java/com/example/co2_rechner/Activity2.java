package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    TextView verbrauchAnzeige;
    TextView kraftstoffAnzeige;
    TextView nameAnzeige;
    TextView streckeAnzeige;
    TextView ergebnis1Anzeige;
    TextView ergebnis2Anzeige;
    TextView treeAnzeige;
    Button infoSeite;
    Button datenbank;
    String anzahlTree;
    Double Ergebnis2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_2);
        verbrauchAnzeige = findViewById(R.id.textView_verbrauchAnzeige);
        streckeAnzeige = findViewById(R.id.textView_streckeAnzeige);
        nameAnzeige = findViewById(R.id.textView_nameAnzeige);
        ergebnis1Anzeige = findViewById(R.id.textView_ergebnis1Anzeige);
        ergebnis2Anzeige = findViewById(R.id.textView_ergebnis2Anzeige);
        treeAnzeige = findViewById(R.id.textView_trees);
        infoSeite = findViewById(R.id.button_InfoSeite);
        datenbank = findViewById(R.id.button_database);
        infoSeite.setOnClickListener(this);
        datenbank.setOnClickListener(this);


        Intent intent = getIntent();
        nameAnzeige.setText(intent.getStringExtra("nameUebergabe"));
        //kraftstoffAnzeige.setText(intent.getStringExtra("kraftstoffUebergabe"));
        verbrauchAnzeige.setText(intent.getStringExtra("verbrauchUebergabe") + " Liter " + intent.getStringExtra("kraftstoffUebergabe") + " auf 100 km");
        streckeAnzeige.setText(intent.getStringExtra("streckeUebergabe") + " Kilometer");
        ergebnis1Anzeige.setText(intent.getStringExtra("ergebnisBerechnung1") + " g Co2/100km");
        ergebnis2Anzeige.setText(intent.getStringExtra("ergebnisBerechnung2") + " g Co2");
        Ergebnis2 = Double.parseDouble(intent.getStringExtra("ergebnisBerechnung2"));
        treeAnzeige.setText(anzahlTree());

    }

    protected String anzahlTree() {
        double treeMenge = Ergebnis2 / 10000;
        anzahlTree = "";

        for (int count = 0; count < treeMenge; count++) {
            anzahlTree = anzahlTree + "\uD83C\uDF33";
        }

        return anzahlTree;

    }

    public void onClick(View view) {
        if (view.getId() ==  R.id.button_InfoSeite) {
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Activity4.class);
            startActivity(intent);

        }

    }
}
