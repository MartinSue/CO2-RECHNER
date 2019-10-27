package com.example.co2_rechner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatenbankManager extends SQLiteOpenHelper{

    private static final String DB_NAME = "BERECHNUNGEN.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "berechnungen";

    public DatenbankManager (Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){

        String createTable = "CREATE TABLE TABLE_NAME " +
                "( IDNote INTEGER PRIMARY KEY AUTOINCREMENT " +
                " , Name TEXT " +
                " , kraftstoff TEXT " +
                " , strecke DOUBLE " +
                " , ergebnisSpezifisch " +
                " , ergebnisAbsolut " +
                ")";
        db.execSQL(createTable);

        /*try{

            db.execSQL(
                    "CREATE TABLE TABLE_NAME (" +
                            "name TEXT NOT NULL );"
                            //"kraftstoff TEXT NOT NULL," +
                            //"verbrauch INTEGER NOT NULL," +
                            //"strecke DOUBLE NOT NULL," +
                            //"ergebnisSpezifisch DOUBLE NOT NULL," +
                            //"ergebnisAbsolut DOUBLE NOT NULL ); "
            );

            db.execSQL("CREATE INDEX mein_index_1 ON berechnungen (name);");


            db.execSQL("INSERT INTO berechnungen VALUES (1, 'Mercedes')");
            db.execSQL("INSERT INTO berechnungen VALUES (1, 'Polo')");


        }catch (Exception e){

        }*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
