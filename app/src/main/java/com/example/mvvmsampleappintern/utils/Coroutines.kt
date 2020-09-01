package com.example.mvvmsampleappintern.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

object Coroutines {
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
}