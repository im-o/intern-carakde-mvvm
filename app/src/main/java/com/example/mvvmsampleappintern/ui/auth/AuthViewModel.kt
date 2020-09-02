package com.example.mvvmsampleappintern.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.repository.UserRepository
import com.example.mvvmsampleappintern.utils.ApiException
import com.example.mvvmsampleappintern.utils.Coroutines
import com.example.mvvmsampleappintern.utils.NoInternetException

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

    fun goLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goSignUp(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

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
                    return@main
                }
            }catch (err: ApiException){
                authListener?.onFailure(err.message.toString())
            }catch (err: NoInternetException){
                authListener?.onFailure(err.message.toString())
            }

        }
    }

    fun onRegisterButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Email is can't be empty")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Password is can't be empty")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userRegister(email.toString(), password.toString())
                authResponse.let {
                    authListener?.onSuccess(it)
                    return@main
                }
            }catch (err: ApiException){
                authListener?.onFailure(err.message.toString())
            }catch (err: NoInternetException){
                authListener?.onFailure(err.message.toString())
            }

        }
    }
}