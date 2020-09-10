package com.example.mvvmsampleappintern.ui.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver : BroadcastReceiver() {
    private val TAG = SmsReceiver::class.java.name
    private val STR_FORMAT = "format"
    private val STR_PDUS = "pdus"

    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle.get(STR_PDUS) as Array<*>
                for (aPdusObj in pdusObj) {
                    val currentMessage = getIncomingMessage(aPdusObj ?: return, bundle)
                    val senderNum = currentMessage.displayOriginatingAddress
                    val message = currentMessage.displayMessageBody
                    Log.d(TAG, "senderNum: $senderNum; message: $message")
                    val showSmsIntent = Intent(context, SmsReceiverActivity::class.java)
                    showSmsIntent.apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum)
                        putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message)
                        context.startActivity(this)
                    }
                }
            }
        } catch (err: Exception) {
            Log.e("SMS ERROR", "Exception SMS Receiver : ${err.message}")
        }
    }

    private fun getIncomingMessage(aObjects: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        currentSMS = if (Build.VERSION.SDK_INT >= 23) {
            val format = bundle.getString(STR_FORMAT)
            SmsMessage.createFromPdu(aObjects as ByteArray, format)
        } else SmsMessage.createFromPdu(aObjects as ByteArray)
        return currentSMS
    }
}
