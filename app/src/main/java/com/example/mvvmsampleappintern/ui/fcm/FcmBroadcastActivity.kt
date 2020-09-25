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
    }

    private lateinit var binding: ActivityFcmBroadcastBinding
    private var numberOfVisitor = 0

    private val addBroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            binding.numberVisitorTV.text = "${++numberOfVisitor}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fcm_broadcast)
        getDeviceToken()
        initAddBroadcastReceiver()
    }

    private fun initAddBroadcastReceiver() {
        val addIntentFilter = IntentFilter(ACTION_ADD)
        registerReceiver(addBroadcastReceiver, addIntentFilter)
    }

    private fun getDeviceToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.e("INI TOKEN","MY DEVICE TOKEN -> ${it.token}")
        }
    }

    override fun onResume() {
        super.onResume()
        val addIntentFilter = IntentFilter(ACTION_ADD)
        registerReceiver(addBroadcastReceiver, addIntentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(addBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(addBroadcastReceiver)
    }
}
