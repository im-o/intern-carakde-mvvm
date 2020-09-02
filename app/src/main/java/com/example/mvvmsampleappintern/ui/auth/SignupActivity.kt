package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.databinding.ActivitySignupBinding
import com.example.mvvmsampleappintern.utils.myToast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        myToast("SignUp Started")
    }

    override fun onSuccess(loginResponse: UserToken) {
        val resResponse = loginResponse.token
        val idUser = loginResponse.id
        val responseResult = "${getString(R.string.register_result)} ID : $idUser Token : $resResponse"
        binding.tvResponse.text = responseResult
    }

    override fun onFailure(msg: String) {
        val responseResult = "${getString(R.string.register_error)} $msg"
        binding.tvResponse.text = responseResult
    }
}
