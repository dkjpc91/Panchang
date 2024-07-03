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
        val imageUrl = "https://i.pinimg.com/564x/c9/0e/3e/c90e3e9c95680cc2b1bf1619f814c24f.jpg"

        val activityToLaunch = HomeActivity::class.java
        if (intent != null && intent.action == "com.example.app.ALARM_ACTION") {

            var title = intent.getStringExtra("title").toString()
            var message = intent.getStringExtra("message").toString()
            var selectedRingtone=intent.getIntExtra("selectedRingtone",R.raw.ram)
            Toast.makeText(context, " Receiver: $title and ringtone: $selectedRingtone", Toast.LENGTH_LONG).show()

            notificationHelper.createNotificationWithImage(imageUrl, title, message, activityToLaunch,selectedRingtone)

        }



        



    }
}