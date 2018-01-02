package br.com.dfn.persistence.model;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    public static final String MINHA_PREFERENCIA = "minha_preferencia";
    public static final String LOGADO = "LOGADO";
    private static MySharedPreference mInstance;
    private static SharedPreferences sharedPreferences;

    public static MySharedPreference getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new MySharedPreference();
            sharedPreferences =
                    ctx.getSharedPreferences(MINHA_PREFERENCIA
                            , Context.MODE_PRIVATE);
        }

        return mInstance;
    }

    public boolean isLogged() {
        return sharedPreferences.getBoolean(LOGADO, false);
    }

    public void setLogged(boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LOGADO, value);
        editor.commit();
    }
}
