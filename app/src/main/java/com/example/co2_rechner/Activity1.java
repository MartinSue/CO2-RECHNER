package com.example.co2_rechner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    //Sonstige Variablen
    String kraftstoffAsString;
    Double ergebnisSpezifisch;
    Double ergebnisAbsolut;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        kraftstoffAsString = parent.getItemAtPosition(position).toString();
        if (kraftstoffAsString.equals("Erdgas (CNG)")) {
            seekbar_textview.setText(seekbar_value + " Kilogramm/100km");
        } else {
            seekbar_textview.setText(seekbar_value + " Liter/100km");
            seekValue = seekbar_value;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

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
        seekbar_textview.setText(seekbar_start + " Liter/100km");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_value = seekbar.getProgress();
                if (kraftstoffAsString.equals("Erdgas (CNG)")) {
                    seekbar_textview.setText(seekbar_value + " Kilogramm/100km");
                } else {
                    seekbar_textview.setText(seekbar_value + " Liter/100km");
                    seekValue = seekbar_value;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Button Berechnung
        button_berechnung = findViewById(R.id.button_berechnung);
        button_berechnung.setOnClickListener(this);

        //Button Hilfe
        button_hilfe = findViewById(R.id.button_hilfe);
        button_hilfe.setOnClickListener(this);

        //EditText
        editText_name = findViewById(R.id.plainText_name);
        getEditText_strecke = findViewById(R.id.plainText_strecke);


    }

    @Override
    public void onClick(View view) {
        try {

            if (view.getId() == R.id.button_berechnung) {

                String strecke = getEditText_strecke.getText().toString();

                if (kraftstoffAsString.equals("Benzin")) {
                    ergebnisSpezifisch = seekbar_value * 23.2;
                    ergebnisAbsolut = rundeBetrag(ergebnisSpezifisch * Double.valueOf(strecke));
                }
                if (kraftstoffAsString.equals("Diesel")) {
                    ergebnisSpezifisch = seekbar_value * 26.5;
                    ergebnisAbsolut = rundeBetrag(ergebnisSpezifisch * Double.valueOf(strecke));
                }
                if (kraftstoffAsString.equals("Autogas (LPG)")) {
                    ergebnisSpezifisch = seekbar_value * 17.9;
                    ergebnisAbsolut = rundeBetrag(ergebnisSpezifisch * Double.valueOf(strecke));
                }
                if (kraftstoffAsString.equals("Erdgas (CNG)")) {
                    ergebnisSpezifisch = seekbar_value * 16.3;
                    ergebnisAbsolut = rundeBetrag(ergebnisSpezifisch * Double.valueOf(strecke));
                }
                if (kraftstoffAsString.equals("Bitte auswählen...")) {
                    Toast.makeText(this, R.string.toastBitteKraftstoff, Toast.LENGTH_SHORT).show();
                    Exception f = new Exception();
                    throw (new Exception(f));
                }



                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra("nameUebergabe", "" + editText_name.getText());
                intent.putExtra("kraftstoffUebergabe", kraftstoffAsString);
                intent.putExtra("verbrauchUebergabe", "" + seekbar_value);
                intent.putExtra("streckeUebergabe", "" + getEditText_strecke.getText());
                intent.putExtra("ergebnisBerechnung1", ergebnisSpezifisch + "");
                intent.putExtra("ergebnisBerechnung2", ergebnisAbsolut + "");

                startActivity(intent);
            }
            if (view.getId() == R.id.button_hilfe) {
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            }

        } catch (Exception e) {
            Toast.makeText(this, R.string.toastBitteNumerischen, Toast.LENGTH_SHORT).show();
        }

    }


    //Methode um Betrag zu runden
    public double rundeBetrag(double betrag) {
        double round = Math.round(betrag * 10000);
        round = round / 10000;
        round = Math.round(round * 1000);
        round = round / 1000;
        round = Math.round(round * 100);
        return round / 100;
    }



}
