package br.com.dfn.servicesample.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.UUID;

import br.com.dfn.servicesample.broadcasts.MyReceiver;

public class MyFirstService extends Service {
    private String uuid = "";

    public MyFirstService() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyFirstService", "onStartCommand:Before(" + uuid + ")");

        MyAsyncTask task = new MyAsyncTask();
        task.execute();

        return START_STICKY;
    }


    private class MyAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.d("MyFirstService", "onStartCommand:After");

            Intent intent = new Intent("MyReceiver.received");
            intent.putExtra("result", "Resultado vem aqui");
            LocalBroadcastManager.getInstance(MyFirstService.this).
                    sendBroadcast(intent);

            super.onPostExecute(o);
        }
    }
}
