package com.example.mvvmsampleappintern.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.myToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}