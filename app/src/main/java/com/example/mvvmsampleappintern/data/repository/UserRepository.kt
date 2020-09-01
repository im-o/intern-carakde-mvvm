package com.example.mvvmsampleappintern.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by rivaldy on Aug/31/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class UserRepository {
    fun userLogin(email: String, password: String): LiveData<UserToken>{
        val loginResponse = MutableLiveData<UserToken>()
        ApiClient.iMyApi.userLogin(email, password)
            .enqueue(object : Callback<UserToken>{
                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    loginResponse.value = null
                }

                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    if (response.isSuccessful){
                        loginResponse.value = response.body()
                    } else {
                        loginResponse.value = null
                    }
                }
            })
        return loginResponse
    }
}