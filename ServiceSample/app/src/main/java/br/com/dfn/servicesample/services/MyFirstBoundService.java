package br.com.dfn.servicesample.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Chronometer;

public class MyFirstBoundService extends Service {

    private IBinder mBinder = new MyBinder();
    private long mBaseElapsed;

    public MyFirstBoundService() {
        mBaseElapsed = SystemClock.elapsedRealtime();
       /* mChronometer = new Chronometer(getApplicationContext());
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();*/
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mChronometer.stop();
    }


    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime()
                - mBaseElapsed;
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds + ":" + millis;
    }


    public class MyBinder extends Binder {
        public MyFirstBoundService getService() {
            return MyFirstBoundService.this;
        }
    }

}
