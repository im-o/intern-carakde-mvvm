package com.example.mvvmsampleappintern.ui.fcm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityFcmBroadcastBinding
import com.google.firebase.iid.FirebaseInstanceId

class FcmBroadcastActivity : AppCompatActivity() {
    companion object {
        const val ACTION_ADD = "action_add"
        const val ACTION_REDUCE = "action_reduce"
    }

    private lateinit var binding: ActivityFcmBroadcastBinding
    private var numberOfVisitor = 0

    private val addBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.numberVisitorTV.text = "${++numberOfVisitor}"
        }
    }

    private val reduceBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.numberVisitorTV.text = if (numberOfVisitor != 0) "${--numberOfVisitor}" else "0"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fcm_broadcast)
        getDeviceToken()
        initAddBroadcastReceiver()
        initReduceBroadcastReceiver()
    }

    private fun initAddBroadcastReceiver() {
        val addIntentFilter = IntentFilter(ACTION_ADD)
        registerReceiver(addBroadcastReceiver, addIntentFilter)
    }

    private fun initReduceBroadcastReceiver() {
        val reduceIntentFilter = IntentFilter(ACTION_REDUCE)
        registerReceiver(reduceBroadcastReceiver, reduceIntentFilter)
    }

    private fun getDeviceToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.e("INI TOKEN","MY DEVICE TOKEN -> ${it.token}")
        }
    }

    override fun onResume() {
        super.onResume()
        val addIntentFilter = IntentFilter(ACTION_ADD)
        val reduceIntentFilter = IntentFilter(ACTION_REDUCE)
        registerReceiver(addBroadcastReceiver, addIntentFilter)
        registerReceiver(reduceBroadcastReceiver, reduceIntentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(addBroadcastReceiver)
        unregisterReceiver(reduceBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(addBroadcastReceiver)
        unregisterReceiver(reduceBroadcastReceiver)
    }
}
