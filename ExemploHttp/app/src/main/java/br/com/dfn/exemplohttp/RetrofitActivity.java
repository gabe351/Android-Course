package br.com.dfn.exemplohttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.com.dfn.exemplohttp.communication.ParamRequest;
import br.com.dfn.exemplohttp.communication.RetrofitAsyncTask;
import br.com.dfn.exemplohttp.model.Article;

public class RetrofitActivity extends AppCompatActivity
        implements RetrofitAsyncTask.GetNewsApiListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        ParamRequest paramRequest = new ParamRequest();
        paramRequest.source = "ign";
        paramRequest.sortBy = "top";
        paramRequest.apiKey = "d268ab1d89a54d01af94ec7fd5f5bbd6";

        RetrofitAsyncTask task = new RetrofitAsyncTask(this);
        task.execute(paramRequest);
    }

    @Override
    public void onNewsApiResult(List<Article> articles) {
        // Adapter -> articles
        // lst.setAdapter(adapter);
        if (articles != null) {
            for (Article article : articles) {
                Log.d("article:retrofit", article.getTitle());
            }
        }
    }
}
