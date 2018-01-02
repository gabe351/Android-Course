package br.com.dfn.exemplohttp.communication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {

    private static String URL_BASE =
            "https://newsapi.org/v1/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
