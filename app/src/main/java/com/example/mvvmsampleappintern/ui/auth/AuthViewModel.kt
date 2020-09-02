package com.example.mvvmsampleappintern.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.repository.UserRepository
import com.example.mvvmsampleappintern.utils.ApiException
import com.example.mvvmsampleappintern.utils.Coroutines

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Email or password is empty")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email.toString(), password.toString())
                authResponse.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
            }catch (err: ApiException){
                authListener?.onFailure(err.message.toString())
            }

        }
    }
}