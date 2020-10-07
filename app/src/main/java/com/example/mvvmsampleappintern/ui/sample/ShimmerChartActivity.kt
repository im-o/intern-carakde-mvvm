package com.example.mvvmsampleappintern.ui.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityShimmerChartBinding

class ShimmerChartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShimmerChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer_chart)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shimmer_chart)
        initView()
    }

    private fun initView() {

    }

    override fun onResume() {
        super.onResume()
        binding.shimmerChartSFL.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerChartSFL.stopShimmer()
    }
}
