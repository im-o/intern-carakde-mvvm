package com.example.mvvmsampleappintern.service

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by rivaldy on Sep/25/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */


class MyFirebaseMessagingService: FirebaseMessagingService() {
    companion object {
        const val TYPE_ADD = "add"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("INI NEW TOKEN", "TOKEN -> $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e("INI MessageReceived","Some Message Received : $remoteMessage")
        when(remoteMessage.data["type"]){
            TYPE_ADD -> {
                val addServiceIntent = Intent(this, AddService::class.java)
                startService(addServiceIntent)
            }
        }
    }
}