package com.example.mvvmsampleappintern.ui.userlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.data.model.UserList
import com.example.mvvmsampleappintern.databinding.ActivityUserListBinding
import com.example.mvvmsampleappintern.ui.auth.AuthViewModelFactory
import com.example.mvvmsampleappintern.utils.*
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
        binding.progressUser.visible()
        Coroutines.main {
            val userResponse = viewModel.getAllUser(samplePage)
            userResponse.data.let {
                binding.progressUser.gone()
                setupAdapter(it)
            }
        }
    }

    private fun setupAdapter(userList: MutableList<UserList>?) {
        userList ?: return
        val userAdapter = AdapterUserList(userList){
            myToast("INI USER -> : ${it.first_name}")
        }
        binding.rvUserList.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }
}
