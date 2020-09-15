package com.example.mvvmsampleappintern.ui.broadcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.databinding.ActivitySmsReceiverBinding

class SmsReceiverActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"
    }

    private lateinit var binding: ActivitySmsReceiverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms_receiver)

        val title = getString(R.string.incoming_message)
        binding.closeMB.setOnClickListener {
            val senderNo = intent?.getStringExtra(EXTRA_SMS_NO)
            val senderMessage = intent?.getStringExtra(EXTRA_SMS_MESSAGE)

            binding.textFromTV.text = getString(R.string.str_from, senderNo)
            binding.textMessageTV.text = senderMessage
        }
    }
}
