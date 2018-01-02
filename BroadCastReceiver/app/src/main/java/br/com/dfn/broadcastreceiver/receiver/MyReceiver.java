package br.com.dfn.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public static final String ACTION = ".receiver.MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null) {
            if (intent.getAction().equals(ACTION)) {
                Toast.makeText(context, "MyReceiver: ACTION - "
                        + ACTION, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
