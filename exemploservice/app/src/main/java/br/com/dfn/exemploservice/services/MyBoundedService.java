package br.com.dfn.exemploservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundedService extends Service {

    private IBinder mybinder = new MyBinder();
    private int value = 2;

    public MyBoundedService() {
    }

    public int getValue() {
        return value;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mybinder;
    }

    public class MyBinder extends Binder {
        public MyBoundedService getBoundedService() {
            return MyBoundedService.this;
        }
    }


}
