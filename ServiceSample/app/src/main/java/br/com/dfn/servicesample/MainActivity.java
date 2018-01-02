package br.com.dfn.servicesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.dfn.servicesample.services.MyFirstIntentService;
import br.com.dfn.servicesample.services.MyFirstService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.btnStartService);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyFirstService.class);
                startService(i);
            }
        });

        View viewIntentService = findViewById(R.id.btnStartIntentService);
        viewIntentService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyFirstIntentService.class);
                startService(i);
            }
        });

        View viewBindService = findViewById(R.id.btnBindService);
        viewBindService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BindServiceActivity.class);
                startActivity(i);
            }
        });
    }
}
