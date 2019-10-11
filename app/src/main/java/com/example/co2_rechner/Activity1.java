package com.example.co2_rechner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


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

    //Sonstige Variablen
    String TEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        //Hilfe
        TextView hilfe = findViewById(R.id.textview_hilfe);
        hilfe.setMovementMethod(LinkMovementMethod.getInstance());

        //Spinner wird definiert und array items aus string.xml gelesen
        Spinner spinner = findViewById(R.id.spinner_kraftstoffart);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kraftstoffart, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //SeekBar
        seekbar = findViewById(R.id.seekbar_kraftstoffverbrauch);
        seekbar_textview = findViewById(R.id.textview_kraftstoffverbrauch_zahl);

        seekbar.setMax(seekbar_max);
        seekbar.setProgress(seekbar_start);
        seekbar_textview.setText(seekbar_start + " Liter/100km");
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_value = seekbar.getProgress();
                seekbar_textview.setText(seekbar_value + " Liter/100km");
                seekValue=seekbar_value;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Button
        button_berechnung = findViewById(R.id.button_berechnung);
        button_berechnung.setOnClickListener(this);

        //EditText
        editText_name = findViewById(R.id.plaintext_name);
        getEditText_strecke = findViewById(R.id.plaintext_strecke);
    }



    //Toast bei Klick auf Item
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TEST = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View view) {

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("nameUebergabe", ""+editText_name.getText());
        intent.putExtra("artUebergabe", ""+TEST);
        intent.putExtra("verbrauchUebergabe", ""+seekbar_value);
        intent.putExtra("streckeUebergabe", ""+getEditText_strecke.getText());
        Toast.makeText(getApplicationContext(),TEST,Toast.LENGTH_SHORT).show();
        startActivity(intent);

        }
}
