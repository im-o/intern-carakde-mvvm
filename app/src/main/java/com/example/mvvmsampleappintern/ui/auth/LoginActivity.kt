package com.example.mvvmsampleappintern.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.db.entities.User
import com.example.mvvmsampleappintern.databinding.ActivityLoginBinding
import com.example.mvvmsampleappintern.ui.broadcast.BroadcastMainActivity
import com.example.mvvmsampleappintern.ui.chart.SampleChartActivity
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
            if (user != null) myToast("${getString(R.string.no_exist_user)} ${user.token}")
        })

        binding.signInMB.setOnClickListener { loginUser() }
        binding.seeUserTV.setOnClickListener { openActivity(UserListActivity::class.java) }
        binding.seeChartTV.setOnClickListener { openActivity(SampleChartActivity::class.java) }
        binding.seeBroadcastTV.setOnClickListener { openActivity(BroadcastMainActivity::class.java) }
    }

    private fun loginUser() {
        onStarted()
        val email = binding.userEmailET.text.toString().trim()
        val password = binding.userPassET.text.toString().trim()

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
        binding.signInLoadPB.visible()
    }

    override fun onSuccess(user: User) {
        binding.signInLoadPB.gone()
        val resResponse = user.token
        val responseResult = "${getString(R.string.response_result)} $resResponse"
        binding.responseTV.text = responseResult
    }

    override fun onFailure(msg: String) {
        binding.signInLoadPB.gone()
        val responseResult = "${getString(R.string.response_error)} $msg"
        binding.responseTV.text = responseResult
    }
}
