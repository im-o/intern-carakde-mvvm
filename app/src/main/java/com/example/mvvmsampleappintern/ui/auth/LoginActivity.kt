package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.databinding.ActivityLoginBinding
import com.example.mvvmsampleappintern.utils.myToast

class LoginActivity : AppCompatActivity(), AuthListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        myToast("Login Started")
    }

    override fun onSuccess(loginResponse: UserToken) {
        val resResponse = loginResponse.token
        val responseResult = "${getString(R.string.response_result)} $resResponse"
        binding.tvResponse.text = responseResult
    }

    override fun onFailure(msg: String) {
        val responseResult = "${getString(R.string.response_result)} $msg"
        binding.tvResponse.text = responseResult
    }
}
