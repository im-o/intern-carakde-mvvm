package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.databinding.ActivitySignupBinding
import com.example.mvvmsampleappintern.utils.myToast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
    }

    override fun onStarted() {
        myToast("SignUp Started")
    }

    override fun onSuccess(user: User) {
        val resResponse = user.token
        val idUser = user.id
        val responseResult = "${getString(R.string.register_result)} ID : $idUser Token : $resResponse"
        binding.responseTV.text = responseResult
    }

    override fun onFailure(msg: String) {
        val responseResult = "${getString(R.string.register_error)} $msg"
        binding.responseTV.text = responseResult
    }
}
