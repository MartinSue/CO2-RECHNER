package com.example.co2_rechner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {

    DatenbankManager _datenbankManager;
    TextView textView_datenbank;
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
            }
        });

    }

    public void showDatabase() {
        Cursor res = _datenbankManager.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing found :(");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append(res.getString(1) + ": ");
            buffer.append("Kraftstoff :" + res.getString(2) + ", ");
            buffer.append("Verbrauch :" + res.getString(3) + ", ");
            buffer.append("Strecke :" + res.getString(4) + ", ");
            buffer.append("Ergebnis Spezifisch :" + res.getString(5) + ", ");
            buffer.append("Ergebnis Absolut :" + res.getString(6) + "\n\n");
        }

        textView_datenbank.setText(buffer.toString());
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
