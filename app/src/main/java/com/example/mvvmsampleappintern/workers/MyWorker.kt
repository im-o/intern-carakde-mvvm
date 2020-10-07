package com.example.mvvmsampleappintern.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmsampleappintern.R
import com.example.mvvmsampleappintern.ui.workmanager.WorkRequestActivity

/**
 * Created by rivaldy on Sep/24/2020.
 * Find me on my lol Github :D -> https://github.com/im-o
 */

class MyWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "work_manager_channel"
        const val KEY_TASK_OUTPUT = "key_task_output"
    }

    override fun doWork(): Result {
        val data = inputData
        val desc = data.getString(WorkRequestActivity.KEY_TASK_DESC)
        showNotification("Hey I am your worker", desc)

        val data1 = Data.Builder()
            .putString(KEY_TASK_OUTPUT, "From Worker -> Task Finish Successfully")
            .build()

        return Result.success(data1)
    }

    private fun showNotification(title: String?, body: String?) {
        val vibrate = longArrayOf(300, 300, 300, 300, 300)
        val notificationManagerCompat = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_next_week_black_24dp)
            .setContentTitle(title ?: "Notification Title")
            .setContentText(body ?: "Notification Body")
            .setVibrate(vibrate)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setSound(alarmSound)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.vibrationPattern = vibrate
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
    }
}