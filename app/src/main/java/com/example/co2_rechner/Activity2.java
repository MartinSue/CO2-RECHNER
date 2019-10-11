package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity2 extends AppCompatActivity {
    TextView verbrauchAnzeige;
    TextView kraftstoffAnzeige;
    TextView nameAnzeige;
    TextView streckeAnzeige;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        verbrauchAnzeige = findViewById(R.id.textView_verbrauchAnzeige);
        streckeAnzeige = findViewById(R.id.textView_streckeAnzeige);
        kraftstoffAnzeige = findViewById(R.id.textView_kraftstoffartAnzeige);
        nameAnzeige = findViewById(R.id.textView_nameAnzeige);

        Intent intent= getIntent();
        nameAnzeige.setText("Name: " +intent.getStringExtra("nameUebergabe"));
        kraftstoffAnzeige.setText("Kraftstoffart: " +intent.getStringExtra("kraftstoffUebergabe"));
        verbrauchAnzeige.setText("Kraftstoffverbrauch: " +intent.getStringExtra("verbrauchUebergabe")+ " Liter auf 100 km");
        streckeAnzeige.setText("Strecke: " +intent.getStringExtra("streckeUebergabe")+ " Kilometer");



    }

}
