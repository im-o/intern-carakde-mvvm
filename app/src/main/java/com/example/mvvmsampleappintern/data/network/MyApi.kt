package com.example.mvvmsampleappintern.data.network

import com.example.mvvmsampleappintern.data.model.UserToken
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserToken>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {
            val gson = GsonBuilder().setLenient().create()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit
                .Builder()
                .baseUrl("https://reqres.in/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(MyApi::class.java)
        }
    }
}