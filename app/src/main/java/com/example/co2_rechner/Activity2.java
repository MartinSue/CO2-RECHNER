package com.example.co2_rechner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {
    DatenbankManager _datenbankManager;

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

        _datenbankManager = new DatenbankManager(this);

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
        verbrauchAnzeige.setText(intent.getStringExtra("verbrauchUebergabe") + getString(R.string.label_textview_liter) + intent.getStringExtra("kraftstoffUebergabe") + getString(R.string.label_textview_auf100Kilometer));
        streckeAnzeige.setText(intent.getStringExtra("streckeUebergabe") + getString(R.string.label_textview_kilometer));
        ergebnisSpezifischAnzeige.setText(intent.getStringExtra("ergebnisSpezifisch"));
        ergebnisAbsolutAnzeige.setText(intent.getStringExtra("ergebnisAbsolut"));
        ErgebnisAbsolut = intent.getStringExtra("ergebnisAbsolut");
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_InfoSeite) {
            Intent intent = new Intent(this, Activity3.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.button_database) {

            Cursor res = _datenbankManager.getAllData();
            if(res.getCount() == 0){
                //Nachricht anzeigen
                showMessage("Error", "Nothing found :(");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("Name :" + res.getString(1) + "\n");
                buffer.append("Kraftstoff :" + res.getString(2) + "\n");
                buffer.append("Verbrauch :" + res.getString(3) + "\n");
                buffer.append("Strecke :" + res.getString(4) + "\n");
                buffer.append("Ergebnis Spezifisch :" + res.getString(5) + "\n");
                buffer.append("Ergebnis Absolut :" + res.getString(6) + "\n\n");
            }

            //Daten anzeigen
            showMessage("Data", buffer.toString());

            /*Intent intent = new Intent(this, Activity4.class);
            startActivity(intent);*/

        }
        if (view.getId() == R.id.button_BaumAnzeige) {
            Intent intent = new Intent(this, Activity5.class);
            intent.putExtra("ergebnisAbsolut", ErgebnisAbsolut);
            startActivity(intent);


        }


    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
