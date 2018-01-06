package br.com.dfn.koltinwithretrofit.util

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun AppCompatActivity.showToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}