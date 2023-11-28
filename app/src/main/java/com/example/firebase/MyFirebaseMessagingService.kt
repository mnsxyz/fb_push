package com.example.firebase

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body
            Log.d("TAG", "onMessageReceived: $title $body")
            sendNotification(title ?: "123", body ?: "456")
        }
        Log.d("TAG", "onMessageReceived: ")
    }

    private fun sendNotification(title: String, body: String) {
        // 알림 매니저를 통해 채널 등록
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_background)
        notificationManager.notify(0, notificationBuilder.build())
        Log.d("TAG", "sendNotification: $title $body")
    }
}