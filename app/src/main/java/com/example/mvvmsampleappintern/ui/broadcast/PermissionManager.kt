package com.example.mvvmsampleappintern.ui.broadcast

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/**
 * Created by rivaldy on Sep/10/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

object PermissionManager {
    fun check(activity: Activity, permission: String, requestCode: Int) {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }
}