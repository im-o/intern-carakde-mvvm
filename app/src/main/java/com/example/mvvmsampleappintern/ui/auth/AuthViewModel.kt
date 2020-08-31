package com.example.mvvmsampleappintern.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Email or password is empty")
            return
        }
        if (email == "admin" && password == "admin") {
            authListener?.onSuccess()
        } else authListener?.onFailure("Invalid email or password")
    }
}