package com.example.co2_rechner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatenbankManager extends SQLiteOpenHelper{

    private static final String DB_NAME = "CO2_RECHNER.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "co2_rechner_datenbank";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "KRAFTSTOFF";
    private static final String COL_4 = "VERBRAUCH";
    private static final String COL_5 = "STRECKE";
    private static final String COL_6 = "ERGEBNIS_SPEZIFISCH";
    private static final String COL_7 = "ERGEBNIS_ABSOLUT";
    public static final String TAG_Datenbank = "Datenbank";


    public DatenbankManager (Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){

    try {
        db.execSQL(
                "CREATE TABLE "+ TABLE_NAME +" (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NAME TEXT NOT NULL, " +
                        "KRAFTSTOFF TEXT NOT NULL," +
                        "VERBRAUCH INTEGER NOT NULL," +
                        "STRECKE DOUBLE NOT NULL," +
                        "ERGEBNIS_SPEZIFISCH TEXT," +
                        "ERGEBNIS_ABSOLUT TEXT ); "
        );

        db.execSQL("CREATE INDEX mein_index_1 ON TABLE_NAME (ID);");

    }catch (Exception e){
        Log.w(TAG_Datenbank, "Datenbanktabelle konnte nicht erstellt werden");
    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Diese Methode wurde mit Ansicht nicht genutzt
    }

    public boolean insertData(String name, String kraftstoff, Integer verbrauch, Double strecke){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, kraftstoff);
        contentValues.put(COL_4, verbrauch);
        contentValues.put(COL_5, strecke);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID =?", new String[] {id});
    }

    public boolean updateData(String id, String ergebnisSpezifisch, String ergebnisAbsolut){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_6, ergebnisSpezifisch);
        contentValues.put(COL_7, ergebnisAbsolut);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {id});
        return true;
    }
}
