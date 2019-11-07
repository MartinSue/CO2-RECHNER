package com.example.co2_rechner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
//TODO: Alle Strings auf R.strings beziehen!

    DatenbankManager _datenbankManager;
    public static final String TAG_Datenbank = "Datenbank";
    public static final String TAG_Eingaben = "Formular";

    String X;
    //Variablen für EditText
    protected EditText editText_name;
    protected EditText getEditText_strecke;
    //Variablen für SeekBar
    protected SeekBar seekbar;
    protected TextView seekbar_textview;
    protected int seekbar_value;
    protected int seekbar_max = 30;
    protected int seekbar_start = 0;

    public static int seekValue = 0;


    //Variable für Button
    protected Button button_berechnung;
    protected Button button_hilfe;
    protected Button button_datenbank_activity1;

    //Sonstige Variablen
    String kraftstoffAsString;
    Double ergebnisSpezifisch;
    Double ergebnisAbsolut;
    boolean flagFuerKilo = false;
    boolean flagFuerErdgas = false;


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        kraftstoffAsString = parent.getItemAtPosition(position).toString();
        if (kraftstoffAsString.equals("Erdgas (CNG)")) {
            seekbar_textview.setText("" + seekbar_value + getText(R.string.label_textview_kiloAufHundert));
            flagFuerErdgas = true;
        } else {
            seekbar_textview.setText("" + seekbar_value + getText(R.string.label_textview_literAufHundert) );
            seekValue = seekbar_value;
            flagFuerErdgas=false;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Die Methode wurde mit Absicht nicht genutzt
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        //Datenbank
        _datenbankManager = new DatenbankManager(this);

        //Spinner wird definiert und array items aus string.xml gelesen
        Spinner spinner = findViewById(R.id.spinner_kraftstoffart);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kraftstoffart, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //SeekBar
        seekbar = findViewById(R.id.seekbar_kraftstoffverbrauch);
        seekbar_textview = findViewById(R.id.textView_kraftstoffverbrauch_zahl);

        seekbar.setMax(seekbar_max);
        seekbar.setProgress(seekbar_start);
        seekbar_textview.setText("" + seekbar_start + getText(R.string.label_textview_literAufHundert));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_value = seekbar.getProgress();
                if (kraftstoffAsString.equals("Erdgas (CNG)")) {
                    seekbar_textview.setText("" + seekbar_value + getText(R.string.label_textview_kiloAufHundert));
                } else {
                    seekbar_textview.setText("" + seekbar_value +  getText(R.string.label_textview_literAufHundert));
                    seekValue = seekbar_value;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Die Methode wurde mit Absicht nicht genutzt
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Die Methode wurde mit Absicht nicht genutzt
            }
        });

        //Button Berechnung
        button_berechnung = findViewById(R.id.button_berechnung);
        button_berechnung.setOnClickListener(this);

        //Button Hilfe
        button_hilfe = findViewById(R.id.button_hilfe);
        button_hilfe.setOnClickListener(this);

        //Button Hilfe
        button_datenbank_activity1 = findViewById(R.id.button_datenbank_activity1);
        button_datenbank_activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datenbank = new Intent(Activity1.this, Activity4.class);
                startActivity(datenbank);
            }
        });

        //EditText
        editText_name = findViewById(R.id.plainText_name);
        getEditText_strecke = findViewById(R.id.plainText_strecke);
    }

    @Override
    public void onClick(View view) {
        try {

            if (view.getId() == R.id.button_berechnung) {

                if (!ueberpruefeEingaben())
                    return;

                String strecke = getEditText_strecke.getText().toString();

                if (kraftstoffAsString.equals("Benzin")) {
                    ergebnisSpezifisch = rundeBetrag(seekbar_value * 23.2, 1);
                    ergebnisAbsolut = ergebnisSpezifisch * Double.valueOf(strecke);
                        ergebnisAbsolut = rundeBetrag(grammOderKilo(ergebnisAbsolut), 1);
                }
                if (kraftstoffAsString.equals("Diesel")) {
                    ergebnisSpezifisch = rundeBetrag(seekbar_value * 26.5, 1);
                    ergebnisAbsolut = ergebnisSpezifisch * Double.valueOf(strecke);
                        ergebnisAbsolut = rundeBetrag(grammOderKilo(ergebnisAbsolut), 1);
                }
                if (kraftstoffAsString.equals("Autogas (LPG)")) {
                    ergebnisSpezifisch = rundeBetrag(seekbar_value * 17.9, 1);
                    ergebnisAbsolut = ergebnisSpezifisch * Double.valueOf(strecke);
                        ergebnisAbsolut = rundeBetrag(grammOderKilo(ergebnisAbsolut), 1);
                }
                if (kraftstoffAsString.equals("Erdgas (CNG)")) {
                    ergebnisSpezifisch = rundeBetrag(seekbar_value * 16.3, 1);
                    ergebnisAbsolut = ergebnisSpezifisch * Double.valueOf(strecke);
                        ergebnisAbsolut = rundeBetrag(grammOderKilo(ergebnisAbsolut), 1);
                }

                //Daten in Datenbank einpflegen
                //Auslagerung Methode (public void AddData)
                Double strecke_zahl = Double.parseDouble(getEditText_strecke.getText().toString());
                boolean isInserted = _datenbankManager.insertData(editText_name.getText().toString(), kraftstoffAsString, seekbar_value, strecke_zahl);
                if(isInserted == true) {
                    Log.v(TAG_Datenbank, "Daten wurden erfolgreich in die Datenbank hinzugefügt");
                }else {
                    Log.w(TAG_Datenbank, "Daten konnten nicht in der Datenbank eingefügt werden");
                }
            }

            //Intent an Activity 2
            Intent intent_Activity2 = new Intent(this, Activity2.class);
            intent_Activity2.putExtra("nameUebergabe", "" + editText_name.getText());
            intent_Activity2.putExtra("kraftstoffUebergabe", kraftstoffAsString);
            intent_Activity2.putExtra("verbrauchUebergabe", "" + seekbar_value);
            intent_Activity2.putExtra("streckeUebergabe", "" + getEditText_strecke.getText());
            intent_Activity2.putExtra("ergebnisSpezifisch", ergebnisSpezifisch + "");
            intent_Activity2.putExtra("ergebnisAbsolut", (""+ ergebnisAbsolut));
            intent_Activity2.putExtra("flagFuerKilo", flagFuerKilo);
            intent_Activity2.putExtra("flagFuerErdgas",flagFuerErdgas);

            startActivity(intent_Activity2);

            if (view.getId() == R.id.button_hilfe) {
                Intent intent_hilfe = new Intent(this, Activity3.class);
                startActivityForResult(intent_hilfe, 123);
            }

        } catch (Exception e) {
            Log.w(TAG_Eingaben, "Fehler bei Eingabe der Werte");
        }
    }

    private double rundeBetrag(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

    private double grammOderKilo(double absolutErgebnis){
        if( absolutErgebnis > 1000){
            absolutErgebnis = absolutErgebnis/1000;
            flagFuerKilo = true;
        }else{
            flagFuerKilo = false;
        }
        return absolutErgebnis;
    }

    public boolean ueberpruefeEingaben(){

        if (editText_name.getText().toString().equals("")){
            Log.w(TAG_Eingaben, "Es wurde kein Name eingegeben");
            Toast.makeText(this, "Bitte Sie einen Namen an!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (kraftstoffAsString.equals("Bitte auswählen...")){
            Log.w(TAG_Eingaben, "Es wurde keine Kraftstoffart abgegeben");
            Toast.makeText(this, "Bitte geben Sie eine Kraftstoffart an!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(seekbar_value == 0){
            Log.w(TAG_Eingaben, "Es wurde kein Verbrauch eingestellt");
            Toast.makeText(this, "Verbrauch darf nicht Null sein!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(getEditText_strecke.getText().toString().equals("")){
            Log.w(TAG_Eingaben, "Es wurde keine Strecke angegeben");
            Toast.makeText(this, "Bitte geben Sie eine Stecke an!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Integer.parseInt(getEditText_strecke.getText().toString()) >= 400000) {
            Log.w(TAG_Eingaben, "Eingegebene Strecke ist zu groß");
            Toast.makeText(this, "Wert darf nicht > 400.000 sein!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }
}
