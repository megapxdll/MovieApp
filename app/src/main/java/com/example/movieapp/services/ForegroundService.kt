package com.example.movieapp.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.movieapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundService: Service(), CoroutineScope by MainScope() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntentWithParentStack(launchIntent)
        val pendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, "some_channel")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setSound(null)
            .build()

        startForeground(12345, notification)

        launch {
            delay(300)
            sendMyBroadcast()
        }
    }

    private fun sendMyBroadcast() {
        val broadcastIntent = Intent()
        broadcastIntent.putExtra(INTENT_SERVICE_DATA, true)
        broadcastIntent.action = INTENT_ACTION_KEY
        sendBroadcast(broadcastIntent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("some_channel", "Progress", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val INTENT_ACTION_KEY = "com.example.a2kotlinwithmvvm.SERVICE_FINISHED_EVENT"
        const val INTENT_SERVICE_DATA = "INTENT_SERVICE_DATA"

        fun start(context: Context) {
            val usualServiceIntent = Intent(context, ForegroundService::class.java)
            context.startService(usualServiceIntent)
        }

        fun stop(context: Context) {
            val usualServiceIntent = Intent(context, ForegroundService::class.java)
            context.stopService(usualServiceIntent)
        }
    }
}