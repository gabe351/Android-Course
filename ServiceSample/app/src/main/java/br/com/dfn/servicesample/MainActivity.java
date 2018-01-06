package br.com.dfn.servicesample;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.dfn.servicesample.broadcasts.MyReceiver;
import br.com.dfn.servicesample.services.MyFirstIntentService;
import br.com.dfn.servicesample.services.MyFirstService;

public class MainActivity extends AppCompatActivity {

    private MyReceiver broadCastReceiver = new MyReceiver();


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyReceiver.received");

        LocalBroadcastManager.getInstance(this).registerReceiver(broadCastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCastReceiver);
    }

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
