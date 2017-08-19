package br.com.dfn.persistence.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.dfn.persistence.model.MySharedPreference;
import br.com.dfn.persistence.R;
import br.com.dfn.persistence.model.database.MyDataBaseContract;
import br.com.dfn.persistence.model.database.MySqlHelper;

public class MainActivity extends AppCompatActivity {

    public MySharedPreference mySharedPreference;
    private MySqlHelper mySqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySqlHelper = new MySqlHelper(getApplicationContext());

        mySharedPreference =
                MySharedPreference.getInstance(getApplicationContext());

//        mySharedPreference.setLogged(true);
//        insert();

        if (mySharedPreference.isLogged()) {
            Intent it = new Intent(this, HomeActivity.class);
            startActivity(it);
        }
    }

    public void OnInsertClick(View v){
        insert();
    }


    public void insert() {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseContract.Carros.COL_NOME, "FUSCA");
        contentValues.put(MyDataBaseContract.Carros.COL_PLACA, "JJH-5253");
        contentValues.put(MyDataBaseContract.Carros.COL_ANO, "1977");
        db.insert(MyDataBaseContract.Carros.TABLE_NAME, null, contentValues);

        db.close();
    }

    public void update(String pNome, String pPlaca, String pAno) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseContract.Carros.COL_NOME, pNome);
        contentValues.put(MyDataBaseContract.Carros.COL_PLACA, pPlaca);
        contentValues.put(MyDataBaseContract.Carros.COL_ANO, pAno);
        db.update(MyDataBaseContract.Carros.TABLE_NAME,
                contentValues,
                " nome = ? and placa = ?",
                new String[]{"FUSCA", "JJH-5253"});


        db.close();

        query(pNome);
    }

    public void onDeleteClick(View v){
        delete("FUSCA");
    }

    public void delete(String pNome) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();

        db.beginTransaction();

        int resultCarros = db.delete(MyDataBaseContract.Carros.TABLE_NAME,
                " nome = ? ",
                new String[]{pNome});

        //db.delete

        //Commit(Success)
        if(resultCarros != -1) {
            db.setTransactionSuccessful();
        }

        db.endTransaction();

        db.close();
    }


    public void query(String pNome) {
        SQLiteDatabase db = mySqlHelper.getWritableDatabase();
        Cursor c = db.query(MyDataBaseContract.Carros.TABLE_NAME,
                new String[]{MyDataBaseContract.Carros.COL_NOME,
                        MyDataBaseContract.Carros.COL_PLACA,
                        MyDataBaseContract.Carros.COL_ANO},
                " nome = ?",
                new String[]{pNome}, null, null, null);

        if (c != null && c.moveToFirst()) {

            int colNomeIndex = c.getColumnIndex(MyDataBaseContract.Carros.COL_NOME);
            int colPlacaIndex = c.getColumnIndex(MyDataBaseContract.Carros.COL_PLACA);
            int colAnoIndex = c.getColumnIndex(MyDataBaseContract.Carros.COL_ANO);

            String nome = c.getString(colNomeIndex);
            String placa = c.getString(colPlacaIndex);
            String ano = c.getString(colAnoIndex);

            c.close();

            Toast.makeText(getApplicationContext(), "Nome: " + nome,
                    Toast.LENGTH_SHORT).show();

            //Para pegar uma lista
            /*do{

            }while(c.moveToNext());*/
        }
    }


    public void getCar(View v) {
        query("FUSCA");
    }
}
