package com.example.mvvmsampleappintern.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmsampleappintern.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by rivaldy on Sep/02/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class NetworkConnectionInterceptor(
    context: Context
): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }
    private fun isInternetAvailable() : Boolean {
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}