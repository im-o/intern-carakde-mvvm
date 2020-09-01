package com.example.mvvmsampleappintern.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmsampleappintern.data.model.UserToken

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<UserToken>)
    fun onFailure(msg: String)
}