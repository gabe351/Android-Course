package br.com.dfn.exemplohttp.communication;

import br.com.dfn.exemplohttp.model.Article;
import br.com.dfn.exemplohttp.model.ResultNewsApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface NewsApiService {
    // articles?source=ign&sortBy=top&apiKey=d268ab1d89a54d01af94ec7fd5f5bbd6
    @GET("articles")
    Call<ResultNewsApi> getNews(@Query("source") String source,
                                @Query("sortBy") String sortBy,
                                @Query("apiKey") String apiKey);

    @PUT("articles")
    Call<ResultNewsApi> putNew(@Body Article article);

}
