package com.example.mvvmsampleappintern.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityMainBinding
import com.example.mvvmsampleappintern.ui.broadcast.BroadcastMainActivity
import com.example.mvvmsampleappintern.ui.chart.SampleChartActivity
import com.example.mvvmsampleappintern.ui.sample.ShimmerChartActivity
import com.example.mvvmsampleappintern.ui.userlist.UserListActivity
import com.example.mvvmsampleappintern.ui.workmanager.WorkRequestActivity
import com.example.mvvmsampleappintern.utils.openActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initClick()
    }

    private fun initClick() {
        binding.seeUserTV.setOnClickListener { openActivity(UserListActivity::class.java) }
        binding.seeChartTV.setOnClickListener { openActivity(SampleChartActivity::class.java) }
        binding.seeBroadcastTV.setOnClickListener { openActivity(BroadcastMainActivity::class.java) }
        binding.seeShimmerChartTV.setOnClickListener { openActivity(ShimmerChartActivity::class.java) }
        binding.seeWorkManagerTV.setOnClickListener { openActivity(WorkRequestActivity::class.java) }
    }
}
