package com.example.mauricio.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.Fragment;

public class Guardar extends SQLiteOpenHelper  {

    String table="CREATE TABLE Cancion(Id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre Text, Cantante Text, Anio INTEGER, Descripcion Text)";

    public Guardar( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
