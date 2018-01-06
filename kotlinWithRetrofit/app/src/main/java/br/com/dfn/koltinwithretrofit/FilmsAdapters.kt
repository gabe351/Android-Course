package br.com.dfn.koltinwithretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.com.dfn.koltinwithretrofit.model.Film

class FilmsAdapters(context: Context?, films: ArrayList<Film>?) : ArrayAdapter<Film>(context, 0, films) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val film = getItem(position)
        var vi: View? = convertView

        if (vi == null) {
            vi = LayoutInflater.from(context).inflate(R.layout.film_adapter, parent, false)
        }

        val tvName = vi?.findViewById<TextView>(R.id.txtFilm)
        tvName?.text = film.nameOfFilm

        return vi
    }
}