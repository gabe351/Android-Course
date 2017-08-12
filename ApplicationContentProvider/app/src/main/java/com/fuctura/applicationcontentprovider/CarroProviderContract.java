package com.fuctura.applicationcontentprovider;

import android.net.Uri;

/**
 * Created by diego on 25/03/15.
 */
public class CarroProviderContract {

    private static final String AUTHORITY = "com.fuctura.exemplocontentprovider";
    private static final String PATH = "carros";
    private static final int TIPO_GERAL = 1;
    private static final int TIPO_ESPECIFICO = 2;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH);
    public static final Uri CONTENT_URI_ESPECIFICO = Uri.parse("content://" + AUTHORITY + "/" + PATH + "/" + TIPO_ESPECIFICO);

    //DB
    public static final String TABLE_CARRO = "carro";
    public static final String COLUMN_ID  = "_id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_PLACA = "placa";
    public static final String COLUMN_ANO   = "ano";


}
