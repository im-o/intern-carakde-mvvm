package com.example.mvvmsampleappintern.ui.broadcast

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivityBroadcastMainBinding

class BroadcastMainActivity : AppCompatActivity() {
    companion object {
        private const val SMS_REQUEST_CODE = 101
    }
    private lateinit var binding: ActivityBroadcastMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_broadcast_main)
        initClick()
    }

    private fun initClick() {
        binding.checkPermitMB.setOnClickListener {
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST_CODE) {
            when (PackageManager.PERMISSION_GRANTED) {
                grantResults[0] -> Toast.makeText(this, getString(R.string.sms_permission_granted), Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, getString(R.string.sms_permission_denied), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
