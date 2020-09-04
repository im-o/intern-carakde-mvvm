package com.example.mvvmsampleappintern.ui.auth

import com.example.mvvmsampleappintern.data.db.entities.User

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(msg: String)
}