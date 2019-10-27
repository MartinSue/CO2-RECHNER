package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    TextView verbrauchAnzeige;
    TextView nameAnzeige;
    TextView streckeAnzeige;
    TextView ergebnisSpezifischAnzeige;
    TextView ergebnisAbsolutAnzeige;
    Button infoSeite;
    Button datenbank;
    Button baumAnzeige;
    String anzahlTree;
    String ErgebnisAbsolut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_2);
        verbrauchAnzeige = findViewById(R.id.textView_verbrauchAnzeige);
        streckeAnzeige = findViewById(R.id.textView_streckeAnzeige);
        nameAnzeige = findViewById(R.id.textView_nameAnzeige);
        ergebnisSpezifischAnzeige = findViewById(R.id.textView_ergebnis1Anzeige);
        ergebnisAbsolutAnzeige = findViewById(R.id.textView_ergebnis2Anzeige);
        infoSeite = findViewById(R.id.button_InfoSeite);
        datenbank = findViewById(R.id.button_database);
        baumAnzeige = findViewById(R.id.button_BaumAnzeige);
        infoSeite.setOnClickListener(this);
        datenbank.setOnClickListener(this);
        baumAnzeige.setOnClickListener(this);


        Intent intent = getIntent();
        nameAnzeige.setText(intent.getStringExtra("nameUebergabe"));
        verbrauchAnzeige.setText(intent.getStringExtra("verbrauchUebergabe") + R.string.liter + intent.getStringExtra("kraftstoffUebergabe") + R.string.auf100Kilometer);
        streckeAnzeige.setText(intent.getStringExtra("streckeUebergabe") + R.string.label_textview_kilometer);
        ergebnisSpezifischAnzeige.setText(intent.getStringExtra("ergebnisBerechnung1"));
        ergebnisAbsolutAnzeige.setText(intent.getStringExtra("ergebnisBerechnung2"));
        ErgebnisAbsolut = intent.getStringExtra("ergebnisBerechnung2");
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_InfoSeite) {
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button_database) {
            Intent intent = new Intent(this, Activity4.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.button_BaumAnzeige) {
            Intent intent = new Intent(this, Activity5.class);
            intent.putExtra("ergebnisAbsolut", ErgebnisAbsolut);
            startActivity(intent);

        }
    }
}
