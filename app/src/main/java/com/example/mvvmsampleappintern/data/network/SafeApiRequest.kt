package com.example.mvvmsampleappintern.data.network

import com.example.mvvmsampleappintern.utils.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by rivaldy on Sep/01/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

abstract class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val strError = response.errorBody().toString()
            val error = StringBuilder()
            strError.let {
                try {
                    error.append(JSONObject(it).getString("error")) //see error response on endpoint API
                } catch (err: JSONException) { }
            }
            error.append("Error Code : ${response.code()}")
            throw ApiException(error.toString())
        }
    }
}