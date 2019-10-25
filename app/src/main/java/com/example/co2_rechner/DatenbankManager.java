package com.example.co2_rechner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatenbankManager extends SQLiteOpenHelper{

    public DatenbankManager (Context context){
        super(context, "berechnung.db", null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        try{

            db.execSQL(
                    "CREATE TABLE berechnungen (" +
                            "name TEXT NOT NULL, " +
                            "kraftstoff TEXT NOT NULL," +
                            "verbrauch INTEGER NOT NULL," +
                            "strecke DOUBLE NOT NULL," +
                            "ergebnisSpezifisch DOUBLE NOT NULL," +
                            "ergebnisAbsolut DOUBLE NOT NULL ); "
            );

            db.execSQL("CREATE INDEX mein_index ON berechnungen (name);");


            db.execSQL("INSERT INTO berechnungen VALUES (1, 'Mercedes')");
            db.execSQL("INSERT INTO berechnungen VALUES (1, 'Polo')");


        }catch (Exception e){

        }

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
