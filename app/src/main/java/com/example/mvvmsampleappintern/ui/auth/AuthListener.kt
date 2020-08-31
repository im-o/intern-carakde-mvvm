package com.example.mvvmsampleappintern.ui.auth

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(msg: String)
}