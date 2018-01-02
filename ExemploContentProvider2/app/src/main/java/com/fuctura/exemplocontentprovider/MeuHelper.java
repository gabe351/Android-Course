package com.fuctura.exemplocontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego on 21/03/2015.
 */
public class MeuHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE = "meubanco";

    //TABLE CARRO
    public static final String TABLE_CARRO = "carro";
    public static final String COLUMN_ID  = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_PLACA = "placa";
    public static final String COLUMN_ANO   = "ano";

    public MeuHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public MeuHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CARRO + " ("+
        COLUMN_ID  + " integer primary key autoincrement, "+
        COLUMN_NOME + " text not null, " +
        COLUMN_PLACA + " text not null, " +
        COLUMN_ANO   +  " text not null); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
              //Atualiza o banco de dados.
          }
    }
}
