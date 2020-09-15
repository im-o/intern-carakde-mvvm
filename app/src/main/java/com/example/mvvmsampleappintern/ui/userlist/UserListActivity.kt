package com.example.mvvmsampleappintern.ui.userlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.model.Data
import com.example.mvvmsampleappintern.databinding.ActivityUserListBinding
import com.example.mvvmsampleappintern.ui.auth.AuthViewModelFactory
import com.example.mvvmsampleappintern.utils.Coroutines
import com.example.mvvmsampleappintern.utils.gone
import com.example.mvvmsampleappintern.utils.myToast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class UserListActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var binding: ActivityUserListBinding
    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        viewModel = ViewModelProvider(this, factory)[UserListViewModel::class.java]
        initViewModel()
    }

    private fun initViewModel() {
        val samplePage = "1"
        Coroutines.main {
            val userResponse = viewModel.getAllUser(samplePage)
            userResponse.user.let {
                hideShimmer()
                setupAdapter(it)
            }
        }
    }

    private fun setupAdapter(userData: MutableList<Data>?) {
        userData ?: return
        val userAdapter = AdapterUserList(userData){
            myToast("${getString(R.string.am_user)} ${it.first_name}")
        }
        binding.userListRV.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }
    private fun hideShimmer() {
        binding.shimmerLoadingSFL.apply {
            stopShimmer()
            gone()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerLoadingSFL.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLoadingSFL.stopShimmer()
    }
}
