package com.example.mvvmsampleappintern.service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import com.example.mvvmsampleappintern.ui.fcm.FcmBroadcastActivity

/**
 * Created by rivaldy on Sep/25/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class AddService: JobIntentService() {

    companion object {
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, AddService::class.java, 123, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        val notifyFinishIntent = Intent(FcmBroadcastActivity.ACTION_ADD)
        sendBroadcast(notifyFinishIntent)
    }
}