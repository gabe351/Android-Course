package br.com.dfn.exemploservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.dfn.exemploservice.services.MyBoundedService;
import br.com.dfn.exemploservice.services.MyFirstService;
import br.com.dfn.exemploservice.services.MyIntentService;

public class MainActivity extends AppCompatActivity {

     private  MyBoundedService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartService = findViewById(R.id.btnStartService);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyFirstService.class);
                startService(i);
            }
        });

        Button btnStarIntenttService = findViewById(R.id.btnStarIntenttService);
        btnStarIntenttService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyIntentService.startActionFoo(MainActivity.this,
                        "P1","p2");
            }
        });

        Button btnBindService = findViewById(R.id.btnBindService);
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Bind service

            }
        });
    }

    public ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundedService.MyBinder myBinder = (MyBoundedService.MyBinder) iBinder;
            service = myBinder.getBoundedService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopService(new Intent(MainActivity.this, MyFirstService.class));
    }
}
