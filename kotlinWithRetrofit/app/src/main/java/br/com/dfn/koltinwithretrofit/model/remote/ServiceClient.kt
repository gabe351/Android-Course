package br.com.dfn.koltinwithretrofit.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClient {
    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}