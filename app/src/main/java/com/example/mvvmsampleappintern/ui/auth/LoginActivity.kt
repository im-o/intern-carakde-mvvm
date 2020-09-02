package com.example.mvvmsampleappintern.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.db.AppDatabase
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.data.network.ApiClient
import com.example.mvvmsampleappintern.data.repository.UserRepository
import com.example.mvvmsampleappintern.databinding.ActivityLoginBinding
import com.example.mvvmsampleappintern.ui.home.HomeActivity
import com.example.mvvmsampleappintern.utils.myToast

class LoginActivity : AppCompatActivity(), AuthListener {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myApi = ApiClient.iMyApi
        val db = AppDatabase(this)
        val repository = UserRepository(myApi, db)
        val factory = AuthViewModelFactory(repository)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel

        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        myToast("Login Started")
    }

    override fun onSuccess(user: User) {
        val resResponse = user.token
        val responseResult = "${getString(R.string.response_result)} $resResponse"
//        binding.tvResponse.text = responseResult
        myToast("Success login : $responseResult")
    }

    override fun onFailure(msg: String) {
        val responseResult = "${getString(R.string.response_error)} $msg"
//        binding.tvResponse.text = responseResult
        myToast("Failed login : $responseResult")
    }
}
