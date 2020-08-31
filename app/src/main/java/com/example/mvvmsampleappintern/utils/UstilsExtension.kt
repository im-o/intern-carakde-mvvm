package com.example.mvvmsampleappintern.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

fun Context.myToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}