package com.example.mvvmsampleappintern.data.repository

import com.example.mvvmsampleappintern.data.db.AppDatabase
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.data.network.ApiClient
import com.example.mvvmsampleappintern.data.network.MyApi
import com.example.mvvmsampleappintern.data.network.SafeApiRequest
import retrofit2.Response

/**
 * Created by rivaldy on Aug/31/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class UserRepository(
    private val myApi: MyApi,
    private val db: AppDatabase
): SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): User{
        return apiRequest {
            myApi.userLogin(email, password)
        }
    }

    suspend fun userRegister(email: String, password: String): User {
        return apiRequest {
            myApi.userRegister(email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().updateInsert(user)

    fun getUser() = db.getUserDao().getUser()
}