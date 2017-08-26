package br.com.dfn.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActitivity extends AppCompatActivity {
    private BaseActitivity.ConnectivityChangeReceiver receiver =
            new BaseActitivity.ConnectivityChangeReceiver();

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter =
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class ConnectivityChangeReceiver
            extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean hasConnection = isConnected(context);
            onConnectivityChange(hasConnection);
        }
    }

    public static boolean isConnected(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo =
                connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(ctx, "Sem conexão !", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void onConnectivityChange(boolean isConnected) {

    }
}
