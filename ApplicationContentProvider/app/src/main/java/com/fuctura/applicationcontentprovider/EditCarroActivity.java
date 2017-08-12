package com.fuctura.applicationcontentprovider;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditCarroActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText edtNome,edtPlaca,edtAno;
    private Button btnSalvar;
    private Carro car;
    private CarroRepository carroRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_carro);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtPlaca = (EditText)findViewById(R.id.edtPlaca);
        edtAno = (EditText)findViewById(R.id.edtAno);
        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);

        carroRepository = new CarroRepository(getApplicationContext());

        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().getLong(CarroProviderContract.COLUMN_ID, 0) != 0) {
                long id = getIntent().getExtras().getLong(CarroProviderContract.COLUMN_ID, 0);

                car = carroRepository.getCarro(getApplicationContext(),id);
                edtNome.setText(car.nome);
                edtPlaca.setText(car.placa);
                edtAno.setText(car.ano);

            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_carro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        updateCar();
    }

    private void updateCar(){
        car.ano = edtAno.getText().toString();
        car.nome = edtNome.getText().toString();
        car.placa = edtPlaca.getText().toString();

        carroRepository.update(car);
    }
}
