package br.com.dfn.broadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ListNewsActivity extends BaseActitivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
    }

    @Override
    public void onConnectivityChange(boolean isConnected) {
        super.onConnectivityChange(isConnected);
        if (!isConnected){
            //show try again;
        }
    }
}
