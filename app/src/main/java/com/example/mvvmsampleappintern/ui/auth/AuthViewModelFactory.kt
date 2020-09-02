package com.example.mvvmsampleappintern.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.data.repository.UserRepository

/**
 * Created by rivaldy on Sep/02/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}