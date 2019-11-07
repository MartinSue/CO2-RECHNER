package com.example.co2_rechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {

    Button button_formelIntent;
    Button button_baumIntent;
    ImageView imageView_baum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_3);
        button_baumIntent = findViewById(R.id.button_WebseiteBaum);
        button_formelIntent =findViewById(R.id.button_WebseiteFormel);


        YoYo.with(Techniques.ZoomIn)
                .duration(5000)
                .playOn(findViewById(R.id.imageView_baum));


        button_baumIntent.setOnClickListener(this);
        button_formelIntent.setOnClickListener(this);

    }
    public void onBackPressed()
    {
        Intent intent = new Intent(this,Activity1.class);
        startActivity(intent);
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
        }  else {
            intent = createIntentFormel();
        }
        //if (Hilfsklasse.wirdIntentUnterstuetzt(this, intent)){
            startActivity(intent);
        //}else{
        //    Toast.makeText(Activity3.this, "Dieser Intent wird auf Ihrem Gerät leider nicht unterstützt.", Toast.LENGTH_LONG);
        //    v.setEnabled(false);
        //}


    }
}
