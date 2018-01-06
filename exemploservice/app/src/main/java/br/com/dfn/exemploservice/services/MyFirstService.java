package br.com.dfn.exemploservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.UUID;

public class MyFirstService extends Service {

    private static final String TAG = MyFirstService.class.getSimpleName();
    private String uuid = "";

    public MyFirstService() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "uuid -> " + uuid);

        MyAsyncTask task = new MyAsyncTask();
        task.execute();
        Log.d(TAG, "START PROCESS");

//        if(intent.getAction().equals("ACTION_1")){
//            //
//        }else if(intent.getAction().equals("ACTION_1")){
//            //
//        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "(DESTROY)  uuid -> " + uuid);
    }


    private class MyAsyncTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "END PROCESS");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}
