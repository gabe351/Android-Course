package br.com.dfn.exemplohttp.communication;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import br.com.dfn.exemplohttp.model.Article;
import br.com.dfn.exemplohttp.model.ResultNewsApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAsyncTask
        extends AsyncTask<ParamRequest,
        Void,
        List<Article>> {

    private GetNewsApiListener getNewsApiListener;

    public RetrofitAsyncTask(GetNewsApiListener listener) {
        this.getNewsApiListener = listener;
    }

    @Override
    protected List<Article> doInBackground(ParamRequest... param) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestApi api = retrofit.create(RequestApi.class);
        ParamRequest parameter = param[0];
        try {
            Response<ResultNewsApi> response =
                    api.getNewsApi(parameter.source,
                            parameter.sortBy,
                            parameter.apiKey).execute();
            if (response.body() != null) {
                return response.body().getArticles();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Article> articles) {
        super.onPostExecute(articles);
        //Retornar a lista de artigos
        if (getNewsApiListener != null) {
            getNewsApiListener.onNewsApiResult(articles);
        }
    }

    public interface GetNewsApiListener {
        void onNewsApiResult(List<Article> articles);
    }
}
