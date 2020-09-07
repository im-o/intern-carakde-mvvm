package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.databinding.ActivityLoginBinding
import com.example.mvvmsampleappintern.ui.userlist.UserListActivity
import com.example.mvvmsampleappintern.utils.*
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

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) myToast("There is user exist : ${user.token}")
        })

        binding.btnSignIn.setOnClickListener { loginUser() }
        binding.tvSeeUser.setOnClickListener { openActivity(UserListActivity::class.java) }
    }

    private fun loginUser() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        Coroutines.main {
            try {
                val authResponse = viewModel.userLogin(email, password)
                authResponse.let {
                    viewModel.saveLoggedInUser(it)
                    onSuccess(it)
                    return@main
                }
            }catch (err: ApiException){
                onFailure(err.message.toString())
                err.printStackTrace()
            }catch (err: NoInternetException){
                onFailure(err.message.toString())
                err.printStackTrace()
            }
        }
    }

    override fun onStarted() {
        myToast("Login Started")
    }

    override fun onSuccess(user: User) {
        val resResponse = user.token
        val responseResult = "${getString(R.string.response_result)} $resResponse"
        binding.tvResponse.text = responseResult
    }

    override fun onFailure(msg: String) {
        val responseResult = "${getString(R.string.response_error)} $msg"
        binding.tvResponse.text = responseResult
    }
}
