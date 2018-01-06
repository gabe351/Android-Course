package br.com.dfn.koltinwithretrofit.model.remote

import br.com.dfn.koltinwithretrofit.model.Film
import com.google.gson.annotations.SerializedName

class FilmsResult(@SerializedName("count") var count: Int, @SerializedName("results") var results: ArrayList<Film>)