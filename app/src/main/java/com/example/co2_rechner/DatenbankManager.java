package com.example.co2_rechner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
                " , verbrauch INTEGER " +
                " , strecke DOUBLE " +
                " , ergebnisSpezifisch DOUBLE" +
                " , ergebnisAbsolut DOUBLE" +
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

    public long add_Note(String name, String kraftstoff, double verbrauch, double strecke, double ergebnisSpezifisch, double ergebnisAbsolut){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("KRAFTSTOFF", kraftstoff);
        values.put("VERBRAUCH", verbrauch);
        values.put("STRECKE", strecke);
        values.put("ERGEBNIS_SPEZIFISCH", ergebnisSpezifisch);
        values.put("ERGEBNIS_ABSOLUT", ergebnisAbsolut);

        long newID = db.insert(TABLE_NAME, null, values);

        if(newID == -1){
            return -1;
        }else{
            return newID;
        }
    }

    public String[] get_Table() throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * " +
                        " FROM TABLE_NAME",
                null
        );

        int resultRows = cursor.getCount();
        if(resultRows == 0)
            return new String[]{};

        String[] resultStrings = new String [resultRows];

        int counter = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            resultStrings[counter] = cursor.getString(0);
            counter++;
        }
        return resultStrings;
    }

}
