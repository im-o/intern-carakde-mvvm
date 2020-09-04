package com.example.mvvmsampleappintern.ui.auth

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.data.repository.UserRepository

/**
 * Created by rivaldy on Aug/28/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun getLoggedInUser() = repository.getUser()
    suspend fun userLogin(email: String, password: String) = repository.userLogin(email, password)
    suspend fun userRegister(email: String, password: String) = repository.userRegister(email, password)
    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)
}