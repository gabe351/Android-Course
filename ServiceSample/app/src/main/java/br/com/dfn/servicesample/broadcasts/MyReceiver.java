package br.com.dfn.servicesample.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("MyReceiver.received")) {
            Toast.makeText(context, intent.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }
}
