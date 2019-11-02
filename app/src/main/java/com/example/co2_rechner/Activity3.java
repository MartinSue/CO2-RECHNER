package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {

    Button button_formelIntent;
    Button button_baumIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        button_baumIntent = findViewById(R.id.button_WebseiteBaum);
        button_formelIntent =findViewById(R.id.button_WebseiteFormel);

        button_baumIntent.setOnClickListener(this);
        button_formelIntent.setOnClickListener(this);


        getSupportActionBar().hide();
    }


    protected Intent createIntentFormel(){
        Uri httpUri = Uri.parse("https://www.deutsche-handwerks-zeitung.de/kraftstoffverbrauch-in-co2-ausstoss-umrechnen/150/3097/57956");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(httpUri);

        return intent;
    }

    protected Intent createIntentBaum(){
        Uri httpUri = Uri.parse("https://www.plant-for-the-planet.org/de/informieren/baeume-sind-genial-2");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(httpUri);

        return intent;

    }
    @Override
    public void onClick(View v) {

        Intent intent = null;
        if(v.getId()== R.id.button_WebseiteBaum){
        intent = createIntentBaum();
        }  else if(v.getId()==R.id.button_WebseiteFormel){
            intent = createIntentFormel();
        }
            startActivity(intent);

    }
}
