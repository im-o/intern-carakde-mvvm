package com.example.mvvmsampleappintern.data.network

import com.example.mvvmsampleappintern.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rivaldy on Aug/31/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

object ApiClient {
    private val gson = GsonBuilder().setLenient().create()
    private val httpInterceptor = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE)
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(httpInterceptor)
        .build()

    private fun myRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://reqres.in/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val iMyApi: MyApi = myRetrofit().create(MyApi::class.java)
}
