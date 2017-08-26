package br.com.dfn.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"BOOT COMPLETE",Toast.LENGTH_SHORT).show();
        /*Intent it = new Intent(context);
        context.startActivity();*/
    }
}
