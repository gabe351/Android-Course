package br.com.dfn.exemplohttp.communication;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.dfn.exemplohttp.model.Article;
import br.com.dfn.exemplohttp.model.ResultNewsApi;
import retrofit2.Response;


public class MyAsyncTask
        extends AsyncTask<String, Void, List<Article>> {

    private MyAsyncTaskListener myAsyncTaskListener;

    public MyAsyncTask(MyAsyncTaskListener callback) {
        this.myAsyncTaskListener = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Article> doInBackground(String... strings) {
        String retorno = null;
        List<Article> list = new ArrayList<>();

        NewsApiService api = ServiceClient.getRetrofit().create(NewsApiService.class);
        try {
            Response<ResultNewsApi> response
                    = api.getNews("ign", "top", "d268ab1d89a54d01af94ec7fd5f5bbd6").execute();

            list = response.body().articleList;
        } catch (IOException e) {
            e.printStackTrace();
        }


        // OKHTTP
      /*  OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strings[0])
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            retorno = response.body().string();

            try {
                JSONObject jsonObject =
                        new JSONObject(retorno);

                JSONArray jsonArticles =
                        jsonObject.getJSONArray("articles");

                for (int i = 0; i < jsonArticles.length(); i++) {
                    JSONObject articleJson =
                            jsonArticles.getJSONObject(i);
                    Article obj = new Article(articleJson.getString("author"),
                            articleJson.getString("description"),
                            articleJson.getString("title"),
                            articleJson.getString("urlToImage"));
                    list.add(obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return list;
    }

    @Override
    protected void onPostExecute(List<Article> result) {
        super.onPostExecute(result);

        if (myAsyncTaskListener != null) {
            myAsyncTaskListener.onPostExecute(result);
        }
    }

    public interface MyAsyncTaskListener {
        void onPostExecute(List<Article> articleList);
    }
}
