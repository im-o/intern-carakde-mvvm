package com.example.mvvmsampleappintern.data.repository

import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.data.network.ApiClient
import retrofit2.Response

/**
 * Created by rivaldy on Aug/31/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class UserRepository {
    suspend fun userLogin(email: String, password: String): Response<UserToken>{
        return ApiClient.iMyApi.userLogin(email, password)
    }
}