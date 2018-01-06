package br.com.dfn.koltinwithretrofit.model.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiStartWars {

    @GET("films")
    fun listRepos(): Call<FilmsResult>
}