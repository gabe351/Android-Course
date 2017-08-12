package com.fuctura.exemplocontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CarroProvider extends ContentProvider {

    private static final String AUTHORITY = "com.fuctura.exemplocontentprovider";
    private static final String PATH = "carros";
    private static final int TIPO_GERAL = 1;
    private static final int TIPO_ESPECIFICO = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);

    private MeuHelper mHelper;


    private static final UriMatcher sUriMatcher;
    static{
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            sUriMatcher.addURI(AUTHORITY,PATH,TIPO_GERAL);
            sUriMatcher.addURI(AUTHORITY,PATH + "/#",TIPO_ESPECIFICO);
    }


    public CarroProvider() {

    }


    @Override
    public boolean onCreate() {
        mHelper = new MeuHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType =  sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long id = 0;
         switch (uriType){
             case TIPO_GERAL:
                 id = db.insertWithOnConflict(MeuHelper.TABLE_CARRO,null,
                         values,SQLiteDatabase.CONFLICT_REPLACE);
                 break;

             default:
                 throw new IllegalArgumentException("Uri Não suportada !");

         }

        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.withAppendedPath(CONTENT_URI,String.valueOf(id));

    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType =  sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int linhasAfetadas = 0;

        switch (uriType){
            case TIPO_GERAL:
                linhasAfetadas =  db.update(MeuHelper.TABLE_CARRO, values, selection,selectionArgs);
                break;
            case TIPO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                linhasAfetadas =  db.update(MeuHelper.TABLE_CARRO, values,
                        MeuHelper.COLUMN_ID + "= ?",new String[]{id});
                break;
            default:
                throw new IllegalArgumentException("Uri Não suportada !");
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI,null);
        return linhasAfetadas;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType =  sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType){
            case TIPO_GERAL:
                rowsDeleted = db.delete(MeuHelper.TABLE_CARRO, selection,selectionArgs);
                break;
            case TIPO_ESPECIFICO:
                String id = uri.getLastPathSegment();
                rowsDeleted = db.delete(MeuHelper.TABLE_CARRO, MeuHelper.COLUMN_ID  + "=?",
                        new String[]{id});
                break;
            default:
                throw new IllegalArgumentException("Uri Não suportada !");
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI,null);
        return rowsDeleted;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType =  sUriMatcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MeuHelper.TABLE_CARRO);

        Cursor cursor = null;
        switch (uriType){
            case TIPO_GERAL:
                cursor = queryBuilder.query(db,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TIPO_ESPECIFICO:
                queryBuilder.appendWhere(MeuHelper.COLUMN_ID + "= ?");
                cursor = queryBuilder.query(db,projection,selection,
                        new String[]{uri.getLastPathSegment()},null,null,null);
                break;
            default:
                throw new IllegalArgumentException("Uri Não suportada !");
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return  cursor;
    }


}

