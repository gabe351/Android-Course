package br.com.dfn.koltinwithretrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.dfn.koltinwithretrofit.model.Film
import br.com.dfn.koltinwithretrofit.model.remote.ApiStartWars
import br.com.dfn.koltinwithretrofit.model.remote.ServiceClient
import br.com.dfn.koltinwithretrofit.model.remote.FilmsResult
import br.com.dfn.koltinwithretrofit.util.showSnackbar
import br.com.dfn.koltinwithretrofit.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lstView.setOnItemClickListener { adapterView, _, pos, _ ->
            run {
                val film = adapterView.getItemAtPosition(pos) as Film
                //showSnackbar(film.nameOfFilm)
                showToast(film.nameOfFilm)
            }
        }

        val service1 = ServiceClient.retrofit.create(ApiStartWars::class.java)


        val service = ServiceClient.retrofit.create(ApiStartWars::class.java)
        service.listRepos().enqueue(object : Callback<FilmsResult> {
            override fun onResponse(call: Call<FilmsResult>?, response: Response<FilmsResult>?) {
                val adapter = FilmsAdapters(applicationContext, response?.body()?.results)
                lstView.adapter = adapter
            }

            override fun onFailure(call: Call<FilmsResult>?, t: Throwable?) {

            }
        })
    }
}
