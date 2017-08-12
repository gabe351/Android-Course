package br.com.dfn.exemplohttp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplohttp.model.Article;
import br.com.dfn.exemplohttp.util.Util;

public class MainActivity extends AppCompatActivity {

    public static final String pURL =
            "https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=d268ab1d89a54d01af94ec7fd5f5bbd6";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Util.isConnected(getApplicationContext())) {
            MinhaAsyncTask minhaAsyncTask = new MinhaAsyncTask();
            minhaAsyncTask.execute(pURL);
        }
    }

    public void okhttp(View v) {
        Intent it = new Intent(getApplicationContext(),
                OkhttpActivity.class);
        startActivity(it);
    }

    public void retrofit(View v) {
        Intent it = new Intent(getApplicationContext(),
                RetrofitActivity.class);
        startActivity(it);
    }

    public class MinhaAsyncTask
            extends AsyncTask<String, Void, List<Article>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Article> doInBackground(String... strings) {
            List<Article> articleList = new ArrayList<>();

            //Conecta com a internet e faz a request
            String url = strings[0];
            InputStream inputStream = Util.getStream(url);

            // Transforma o InputStrim em uma String
            String body = Util.streamToString(inputStream);

            // Faz o parse de String para JsonObject em seguida para
            // uma lista de artigos
            articleList = Util.parse(body);

            return articleList;
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
