package br.com.dfn.exemplohttp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplohttp.model.Article;
import br.com.dfn.exemplohttp.util.Util;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        if (Util.isConnected(getApplicationContext())) {
            OkhttpAsyncTask minhaAsyncTask = new OkhttpAsyncTask();
            minhaAsyncTask.execute(MainActivity.pURL);
        }
    }

    public class OkhttpAsyncTask
            extends AsyncTask<String, Void, List<Article>> {

        @Override
        protected List<Article> doInBackground(String... strings) {
            List<Article> list = new ArrayList<>();
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                String url = strings[0];
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response =
                        okHttpClient.newCall(request).execute();

                String body = response.body().string();

                response.close();

                return Util.parse(body);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            for (Article obj : articles) {
                Log.d("Article", "nome: " + obj.getTitle());
            }
        }
    }
}
