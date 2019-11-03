package com.example.co2_rechner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity4 extends AppCompatActivity {

    DatenbankManager _datenbankManager;
    ListView textView_datenbank;
    EditText plainText_id;
    Button button_loeschen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        getSupportActionBar().hide();

        _datenbankManager = new DatenbankManager(this);

        textView_datenbank = findViewById(R.id.textView_datenbank);
        plainText_id = findViewById(R.id.plainText_id);
        button_loeschen = findViewById(R.id.button_loeschen);

        showDatabase();

        button_loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = _datenbankManager.deleteData(plainText_id.getText().toString());
                if(deletedRows > 0) {
                    Toast.makeText(Activity4.this, "Datensatz wurde gelöscht", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Activity4.this, "Löschen fehlgeschlagen", Toast.LENGTH_LONG).show();
                }
                showDatabase();
                plainText_id.setText("");
            }
        });
    }

    public void showDatabase() {
        Cursor res = _datenbankManager.getAllData();
        if (res.getCount() == 0)
            showMessage("Error", "Nothing found ☹");

         ArrayList<String> arrayList=new ArrayList<>();


                String buffer = new String();

        while (res.moveToNext()) {
            buffer = res.getString(0) + ". " + res.getString(1)
                     +  System.getProperty("line.separator") + "Kraftstoff: " + res.getString(2)
                    + System.getProperty("line.separator") + "Verbrauch: " + res.getString(3) + " Liter bzw. KG auf 100 km"
                    + System.getProperty("line.separator") + "Strecke: " + res.getString(4) + " km "+
                    System.getProperty("line.separator") + "➤ Ergebnis Spezifisch: " + res.getString(5) +
                    System.getProperty("line.separator") + "➤ Ergebnis Absolut : " + res.getString(6);


            arrayList.add(buffer);
        }




        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        textView_datenbank.setAdapter(arrayAdapter);


    }


    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
