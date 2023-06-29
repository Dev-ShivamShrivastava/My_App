package com.example.myapp.services

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapp.R
import com.example.myapp.views.activity.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {


    val TAG = "FirebaseMessagingService"


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("LongLogTag")
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(TAG, "remoteMessage : ${p0.notification?.title}")
        Log.d(TAG, "remoteMessage : ${p0.notification?.body}")
        Log.d(TAG, "remoteMessage Message data payload: ${p0.data}")

        showNotification(
            p0.notification?.title,
            p0.notification?.body
        )
    }


    @SuppressLint("LongLogTag")
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(title: String?, msg: String?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = Intent.ACTION_MAIN
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        val channelid = "Human Readable" //for temp


        val notificationBuilder = Notification.Builder(this, channelid)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(title)
            .setContentText(msg)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelid, "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(soundUri, null)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }


}