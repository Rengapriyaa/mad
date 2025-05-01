package com.example.winwin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class NotificationReceiver : BroadcastReceiver() {
    private val channel_id="mychannel"
    private val noti_id=1
    override fun onReceive(context: Context?, intent: Intent?) {
        val builder=NotificationCompat.Builder(context,channel_id)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("bday notification")
            .setContentText("Happy birthday dr")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
        val nm=NotificationManagerCompat.from(context)
        nm.notify(noti_id,builder.build())
    }

}