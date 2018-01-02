package br.com.dfn.exemplohttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.dfn.exemplohttp.communication.MyAsyncTask;
import br.com.dfn.exemplohttp.model.Article;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.MyAsyncTaskListener {
    private String url = "https://newsapi.org/v1/articles?source=ign&sortBy=top&apiKey=d268ab1d89a54d01af94ec7fd5f5bbd6";

    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute(url);
    }

    public void getNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Util.isConnected(getApplicationContext())) {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String retorno = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onPostExecute(List<Article> result) {
        //Exibir os artigos:
        for (Article article : result) {
            Log.d("article","article: " + article.title);
        }
    }
}
