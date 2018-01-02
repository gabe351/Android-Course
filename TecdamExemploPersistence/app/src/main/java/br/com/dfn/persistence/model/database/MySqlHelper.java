package br.com.dfn.persistence.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;
    private static String DATABASE_NAME = "persistence";

    public MySqlHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table carros (_id INTEGER primary key autoincrement, " +
                "nome TEXT, placa TEXT, ano TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        //if(oldVersion != newVersion){}

    }
}
