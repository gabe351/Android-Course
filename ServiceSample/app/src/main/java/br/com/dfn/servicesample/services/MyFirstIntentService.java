package br.com.dfn.servicesample.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.UUID;

public class MyFirstIntentService extends IntentService {
    private String uuid;

    public MyFirstIntentService() {
        super("MyFirstIntentService");
        uuid = UUID.randomUUID().toString();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d("MyFirstIntentService", "onHandleIntent:Before(" + uuid + ")");
            MyAsyncTask task = new MyAsyncTask();
            task.execute();
        }
    }

    private class MyAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.d("MyFirstIntentService", "onHandleIntent:After");

            super.onPostExecute(o);
        }
    }

}
