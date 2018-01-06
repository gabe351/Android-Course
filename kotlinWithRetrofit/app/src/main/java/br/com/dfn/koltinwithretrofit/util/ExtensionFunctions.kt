package br.com.dfn.koltinwithretrofit.util

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.showSnackbar(msg: String) {
    Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show()
}