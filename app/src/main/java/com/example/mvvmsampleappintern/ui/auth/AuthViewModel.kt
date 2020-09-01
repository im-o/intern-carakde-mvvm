package com.example.mvvmsampleappintern.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.repository.UserRepository
import com.example.mvvmsampleappintern.utils.Coroutines

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

        Coroutines.main {
            val response = UserRepository().userLogin(email.toString(), password.toString())
            if (response.isSuccessful){
                authListener?.onSuccess(response.body() ?: return@main)
            } else{
                authListener?.onFailure("ERROR CODE : ${response.code()}")
            }
        }
    }
}