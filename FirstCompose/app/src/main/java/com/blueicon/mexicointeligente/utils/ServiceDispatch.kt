package com.blueicon.mexicointeligente.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.blueicon.mexicointeligente.R

class ServiceDispatch: Service() {

    companion object {
        const val CHANNEL_ID = "ForegroundServiceChannel"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Service is Running")
            .setContentText("Performing background task...")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Replace with your own icon
            .build()

        // Promote the service to a foreground service within 5 seconds
        ServiceCompat.startForeground(this, 1, notification, 0)

        // Perform your background work here (e.g., in a coroutine)
        Log.d("MyForegroundService", "Service started, doing work...")

        // START_STICKY means the system will try to re-create the service if it's killed
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyForegroundService", "Service destroyed.")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

}