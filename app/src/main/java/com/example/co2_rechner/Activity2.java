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
    boolean flagFuerKilo;
    boolean flagFuerErdgas;



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

        flagFuerKilo= intent.getBooleanExtra("flagFuerKilo",false);
        flagFuerErdgas= intent.getBooleanExtra("flagFuerErdgas", false);
        nameAnzeige.setText(intent.getStringExtra("nameUebergabe"));

        // Überprüfung ob Erdgas, da dann Kilogramm angezeigt werden muss mithilfe eines boolean, der von Activity 1 mitgegeben wurde.
        if(flagFuerErdgas==true){
            verbrauchAnzeige.setText(intent.getStringExtra("verbrauchUebergabe") + getString(R.string.label_textview_kilogramm) + intent.getStringExtra("kraftstoffUebergabe") + getString(R.string.label_textview_auf100Kilometer));
        }else{
            verbrauchAnzeige.setText(intent.getStringExtra("verbrauchUebergabe") + getString(R.string.label_textview_liter) + intent.getStringExtra("kraftstoffUebergabe") + getString(R.string.label_textview_auf100Kilometer));
        }

        streckeAnzeige.setText(""+ getString(R.string.label_textview_strecke2)+intent.getStringExtra("streckeUebergabe") + getString(R.string.label_textview_kilometer));
        ergebnisSpezifischAnzeige.setText(intent.getStringExtra("ergebnisSpezifisch")+getText(R.string.label_textview_gramm));
        ErgebnisAbsolut = intent.getStringExtra("ergebnisAbsolut");

        // Überprüfung ob Ergebnis in Kilogramm oder Gramm angezeigt werden muss mithilfe eines boolean, der von Activity 1 mitgegeben wurde.

        if (flagFuerKilo ==true){
            ergebnisAbsolutAnzeige.setText("" + intent.getStringExtra("ergebnisAbsolut")+ getText(R.string.label_textview_kilogrammCO2));
        }else{
            ergebnisAbsolutAnzeige.setText(intent.getStringExtra("ergebnisAbsolut")+ getText(R.string.label_textview_gramm));
        }

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
            intent.putExtra("flagFuerKilo",flagFuerKilo);
            intent.putExtra("ergebnisAbsolut", ErgebnisAbsolut);
            startActivity(intent);
        }
    }
}
