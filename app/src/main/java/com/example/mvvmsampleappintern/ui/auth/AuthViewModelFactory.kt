package com.example.mvvmsampleappintern.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.data.repository.UserRepository

/**
 * Created by rivaldy on Sep/02/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AuthViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(UserRepository::class.java)
            return constructor.newInstance(repository)
        }catch (err: Exception){
            Log.e("ERROR INI","INI -> $err")
        }
        return super.create(modelClass)
    }
}