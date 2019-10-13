package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    TextView verbrauchAnzeige;
    TextView kraftstoffAnzeige;
    TextView nameAnzeige;
    TextView streckeAnzeige;
    TextView ergebnis1Anzeige;
    TextView ergebnis2Anzeige;
    TextView treeAnzeige;
    Button infoSeite;
    String anzahlTree;
    Double Ergebnis2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        verbrauchAnzeige = findViewById(R.id.textView_verbrauchAnzeige);
        streckeAnzeige = findViewById(R.id.textView_streckeAnzeige);
        kraftstoffAnzeige = findViewById(R.id.textView_kraftstoffartAnzeige);
        nameAnzeige = findViewById(R.id.textView_nameAnzeige);
        ergebnis1Anzeige = findViewById(R.id.textView_ergebnis1Anzeige);
        ergebnis2Anzeige = findViewById(R.id.textView_ergebnis2Anzeige);
        treeAnzeige = findViewById(R.id.textView_tree);
        infoSeite = findViewById(R.id.button_InfoSeite);
        infoSeite.setOnClickListener(this);


        Intent intent = getIntent();
        nameAnzeige.setText("Name: " + intent.getStringExtra("nameUebergabe"));
        kraftstoffAnzeige.setText("Kraftstoffart: " + intent.getStringExtra("kraftstoffUebergabe"));
        verbrauchAnzeige.setText("Kraftstoffverbrauch: " + intent.getStringExtra("verbrauchUebergabe") + " Liter auf 100 km");
        streckeAnzeige.setText("Strecke: " + intent.getStringExtra("streckeUebergabe") + " Kilometer");
        ergebnis1Anzeige.setText("Co2 Belastung pro 100km : " + intent.getStringExtra("ergebnisBerechnung1") + " Kilogramm Co2/100km");
        ergebnis2Anzeige.setText("Co2 Belastung insgesamt: " + intent.getStringExtra("ergebnisBerechnung2") + " Kilogramm Co2");

        Ergebnis2 = Double.parseDouble(intent.getStringExtra("ergebnisBerechnung2"));
        treeAnzeige.setText(anzahlTree());

    }

    protected String anzahlTree() {
        double treeMenge = Ergebnis2 / 2600;
        anzahlTree = "";

        for (int count = 0; count <= treeMenge; count++) {
            anzahlTree = anzahlTree + "\uD83C\uDF32";
        }

        return anzahlTree;

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);

    }
}
