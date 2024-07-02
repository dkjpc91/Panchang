package com.mithilakshar.mithilapanchang.Utility
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.UI.View.HomeActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        // Do your task here, like showing a toast
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
        val notificationHelper = NotificationHelper(context, R.drawable.ic_launcher_background)
        val imageUrl = "https://i.pinimg.com/564x/44/ca/c9/44cac9ad222f947f6f128b6491c009a2.jpg"
        var title="Title"
        var message="Message"
        if (intent != null && intent.action == "com.example.app.ALARM_ACTION") {
             title = intent.getStringExtra("title").toString()
             message = intent.getStringExtra("message").toString()



        }
        Toast.makeText(context, "AlarmReceiver Received alarm with title: $title and message: $message", Toast.LENGTH_SHORT).show()


        val activityToLaunch = HomeActivity::class.java
        notificationHelper.createNotificationWithImage(imageUrl, title, message, activityToLaunch)


    }
}