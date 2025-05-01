package com.example.winwin

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class notiact : AppCompatActivity() {
    private val channel_id="mychannel"
    private val noti_id=1
    private lateinit var dp:DatePicker
    private lateinit var tp:TimePicker
    private lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notiact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createNotificationchannel()
        dp=findViewById(R.id.dp)
        tp=findViewById(R.id.tp)
        btn=findViewById(R.id.nobut)
        btn.setOnClickListener {
            val cal=Calendar.getInstance()
            val hour=if(Build.VERSION.SDK_INT>=24) tp.hour else tp.currentHour
            val minute=if(Build.VERSION.SDK_INT>=24) tp.minute else tp.currentMinute
            cal.set(dp.year,dp.month,dp.dayOfMonth,hour,minute,0)
            scheduleNotification(cal.timeInMillis)
        }

    }
    private fun scheduleNotification(timeInMillis: Long) {
        val intent= Intent(this,NotificationReceiver::class.java)
        val pendingintent=PendingIntent.getBroadcast(
            this,0,intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmmanager=getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmmanager.setExact(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingintent
        )
        Toast.makeText(this,"notification sent",Toast.LENGTH_SHORT).show()
    }
    private fun createNotificationchannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val name = "birthday"
            val descriptiontext = "Happy birthday dr"
            val important = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channel_id, name, important).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    description = descriptiontext
                }
            }
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}