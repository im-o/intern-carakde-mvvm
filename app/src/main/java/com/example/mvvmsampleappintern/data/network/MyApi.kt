package com.example.mvvmsampleappintern.data.network

import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.data.model.UserToken
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by rivaldy on Aug/31/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

interface MyApi {
    @FormUrlEncoded
    @POST("login")

    suspend fun userLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ): Response<User>
}