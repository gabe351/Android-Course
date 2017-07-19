package com.dfn.sampledialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAlertDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                };

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Attention")
                        .setMessage("Would you like remove?")
                        .setPositiveButton("Yes", listener)
                        .setNegativeButton("No", listener)
                        .setNeutralButton("Cancel", listener)
                        .create();
                alertDialog.show();
            }
        });

        findViewById(R.id.btnDatepickerDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfyear, int dayOfMonth) {
                        Toast.makeText(getApplicationContext(),
                                "Date: " + dayOfMonth + "/" + monthOfyear + "/" + year
                                , Toast.LENGTH_LONG).show();
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, listener, 2017, 07, 18);
                dialog.show();
            }
        });

        findViewById(R.id.btnTimePickerDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(),
                                "Hour: " + hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
                    }
                };

                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this,
                        onTimeSetListener, 13, 30, true);
                dialog.show();
            }
        });
    }
}