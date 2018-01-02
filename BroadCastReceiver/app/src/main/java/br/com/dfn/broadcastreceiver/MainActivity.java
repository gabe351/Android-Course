package br.com.dfn.broadcastreceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.dfn.broadcastreceiver.notification.NewMessageNotification;
import br.com.dfn.broadcastreceiver.receiver.MyReceiver;

public class MainActivity extends BaseActitivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSendBroadCast)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent(getApplicationContext(),
                                MyReceiver.class);
                        //it.setAction(MyReceiver.ACTION);
                        sendBroadcast(it);


                        Intent intent = new Intent(MainActivity.this,
                                ListNewsActivity.class);
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(getApplicationContext())
                                        .setContentText("Minha Notificação")
                                        .setContentTitle("PROMOÇÃO !!!! [" + SystemClock.elapsedRealtime() + "]")
                                        .setContentIntent(
                                                PendingIntent.getActivity(
                                                        getApplicationContext(),
                                                        0,
                                                        intent,
                                                        PendingIntent.FLAG_UPDATE_CURRENT))
                                        .setSmallIcon(R.drawable.ic_stat_new_message);


                        NotificationManager notificationManagerCompat =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        int MINHA_NOTIFICATION = 1;

                        notificationManagerCompat.notify(MINHA_NOTIFICATION,
                                builder.build());

                    }
                });
    }

    @Override
    public void onConnectivityChange(boolean isConnected) {
        super.onConnectivityChange(isConnected);

        int visible = isConnected == true ? View.GONE : View.VISIBLE;
        findViewById(R.id.txtNoConnection).setVisibility(visible);

        if (!isConnected) {
            NewMessageNotification.notify(this, "Sem conexão !", 0, R.drawable.ic_stat_new_message);
        }
    }
}
