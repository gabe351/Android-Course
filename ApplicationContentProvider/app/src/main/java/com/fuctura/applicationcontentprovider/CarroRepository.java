package com.fuctura.applicationcontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by diego on 23/03/15.
 */
public class CarroRepository {

    private Context context;

    public CarroRepository(Context ctx){
        this.context = ctx;

    }

    public long insert(Carro carro){
        Uri uri = context.getContentResolver().insert(CarroProviderContract.CONTENT_URI,
                getValues(carro));
        long id = Long.parseLong(uri.getLastPathSegment());

        if(id  != -1){
            carro._id = id;
        }

        return id;
    }

    public int update(Carro carro){
        Uri uri =  Uri.withAppendedPath(CarroProviderContract.CONTENT_URI, String.valueOf(carro._id));
        int rowsAffected = context.getContentResolver().update(uri, getValues(carro),null,null);
        return  rowsAffected;
    }


    public int delete(Carro carro){
        Uri uri = Uri.withAppendedPath(CarroProviderContract.CONTENT_URI, String.valueOf(carro._id));

        int rowsAffected = context.getContentResolver().delete(uri,null,null);

        return rowsAffected;
    }

    public CursorLoader find(Context c, String s){
        String where = null;
        String[] whereArgs = null;
        if(s != null){
            where = CarroProviderContract.COLUMN_NOME + " LIKE ?";
            whereArgs = new String[]{"%" + s + "%"};
        }

        return new CursorLoader(
                c,
                CarroProviderContract.CONTENT_URI,
                null,
                where,
                whereArgs,
                CarroProviderContract.COLUMN_NOME
        );
    }

    public Carro getCarro(Context c, long id){

        Uri uri = Uri.withAppendedPath(CarroProviderContract.CONTENT_URI, String.valueOf(id));

        String[] projection = {CarroProviderContract.COLUMN_ID,
                CarroProviderContract.COLUMN_NOME,
                CarroProviderContract.COLUMN_PLACA,
                CarroProviderContract.COLUMN_ANO};

        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);

        return CarroRepository.getCarFromCursor(cursor);
    }




    public static Carro getCarFromCursor(Cursor cursor){
        if(cursor == null || !cursor.moveToFirst()){
            return null;
        }

        long id = cursor.getLong(cursor.getColumnIndex(CarroProviderContract.COLUMN_ID));

        String nome = cursor.getString(cursor.getColumnIndex(CarroProviderContract.COLUMN_NOME));
        String placa = cursor.getString(cursor.getColumnIndex(CarroProviderContract.COLUMN_PLACA));
        String ano = cursor.getString(cursor.getColumnIndex(CarroProviderContract.COLUMN_ANO));

        return new Carro(id,nome,placa,ano);
    }


    private ContentValues getValues(Carro carro){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CarroProviderContract.COLUMN_NOME,carro.nome);
        contentValues.put(CarroProviderContract.COLUMN_PLACA,carro.placa);
        contentValues.put(CarroProviderContract.COLUMN_ANO,carro.ano);
        return contentValues;
    }
}
