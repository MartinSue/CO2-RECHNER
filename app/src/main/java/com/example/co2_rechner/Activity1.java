package com.example.co2_rechner;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends Activity implements AdapterView.OnItemSelectedListener {

    //Variablen für SeekBar
    protected SeekBar seekbar;
    protected TextView seekbar_textview;
    protected int seekbar_value;
    protected int seekbar_max = 30;
    protected int seekbar_start = 0;


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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //Toast bei Klick auf Item
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
