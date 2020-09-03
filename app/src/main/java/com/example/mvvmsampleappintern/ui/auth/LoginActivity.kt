package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.model.UserToken
import com.example.mvvmsampleappintern.databinding.ActivityLoginBinding
import com.example.mvvmsampleappintern.utils.ApiException
import com.example.mvvmsampleappintern.utils.NoInternetException
import com.example.mvvmsampleappintern.utils.myToast
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        binding.btnSignIn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userLogin(email, password)
                authResponse.let {
                    myToast("Response : ${it.token}")
                    return@launch
                }
            }catch (err: ApiException){
                err.printStackTrace()
            }catch (err: NoInternetException){
                err.printStackTrace()
            }
        }
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
        val responseResult = "${getString(R.string.response_error)} $msg"
        binding.tvResponse.text = responseResult
    }
}
