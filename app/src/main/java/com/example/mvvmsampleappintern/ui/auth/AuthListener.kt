package com.example.mvvmsampleappintern.ui.auth

import androidx.lifecycle.LiveData

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(msg: String)
}