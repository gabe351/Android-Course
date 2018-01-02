package br.com.dfn.servicesample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import br.com.dfn.servicesample.services.MyFirstBoundService;

public class BindServiceActivity extends AppCompatActivity {

    private boolean mServiceBound = false;
    MyFirstBoundService mBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);

        Button btnBindService = findViewById(R.id.button);
        Button btnGetValue = findViewById(R.id.btnGetValue);
        final TextView timestampText = findViewById(R.id.textView);

        btnBindService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindServiceActivity.this,
                        MyFirstBoundService.class);
                if (mServiceBound) {
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                    stopService(intent);
                } else {
                    startService(intent);
                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
                }
            }
        });

        btnGetValue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mServiceBound) {
                    timestampText.setText(mBoundService.getTimestamp());
                }
            }
        });

    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyFirstBoundService.MyBinder myBinder = (MyFirstBoundService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };
}
