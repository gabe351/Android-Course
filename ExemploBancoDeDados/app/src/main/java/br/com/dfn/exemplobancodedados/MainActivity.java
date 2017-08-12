package br.com.dfn.exemplobancodedados;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplobancodedados.model.MeuOpenHelper;

public class MainActivity extends AppCompatActivity {

    MeuOpenHelper meuOpenHelper;
    TextView txtCarro;
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuOpenHelper =
                new MeuOpenHelper(getApplicationContext());

        txtCarro = (TextView) findViewById(R.id.txtCarro);
        edtName = (EditText) findViewById(R.id.edtNome);
    }

    public void insert(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String nome = edtName.getText().toString();
        contentValues.put("name", nome);
        contentValues.put("ano", "1970");

        long idCarro = db.insert("carros", null, contentValues);
        db.close();

        Toast.makeText(getApplicationContext(),
                "IdCarro: " + idCarro,
                Toast.LENGTH_SHORT).show();
    }

    public void getCar(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String table = "carros";
        String nome = edtName.getText().toString();
        String[] projection = {"name", "ano"};

        String selection = "name = ? and ano = ?";
        String[] selectionArg = {nome, "1970"};

        Cursor c =
                db.query(table,
                        projection,
                        selection,
                        selectionArg,
                        null,
                        null,
                        null,
                        null);

        if (c.moveToFirst()) {
            nome = c.getString(c.getColumnIndex("name"));
            String ano = c.getString(c.getColumnIndex("ano"));
            txtCarro.setText(nome + " - Ano: " + ano);
        }
    }

    public void update(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String nome = edtName.getText().toString();
        contentValues.put("name", nome);
        String where = "name = ? and ano = ?";
        String[] whereArg = {"Fusca", "1970"};

        db.beginTransaction();
        db.update("carros", contentValues, where, whereArg);
        db.endTransaction();
    }

    public void delete(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        String whereClause = "name = ?";
        String nome = edtName.getText().toString();
        String[] whereArgs = {nome};

        db.delete("carros", whereClause, whereArgs);
    }

    public void listCar(View v) {
        SQLiteDatabase db = meuOpenHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from carros", null);
        c.moveToFirst();
        List<String> carrosStringList = new ArrayList();

        while (c.moveToNext()) {
            String nome = c.getString(c.getColumnIndex("name"));
            carrosStringList.add(nome);
        }

        for (String name : carrosStringList) {
            Log.d("carros", "nome: " + name);
        }
    }
}
