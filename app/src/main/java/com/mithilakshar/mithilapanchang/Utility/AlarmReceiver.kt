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
        Toast.makeText(context, "मिथिला पंचांग अलार्म", Toast.LENGTH_SHORT).show()
        val notificationHelper = NotificationHelper(context, R.drawable.logo)

        val activityToLaunch = HomeActivity::class.java
        if (intent != null && intent.action == "com.example.app.ALARM_ACTION") {

            var title = intent.getStringExtra("title").toString()
            var message = intent.getStringExtra("message").toString()
            var selectedRingtone=intent.getIntExtra("selectedRingtone",R.raw.ram)
            val imageUrl =intent.getStringExtra("imageUrl").toString()


            notificationHelper.createNotificationWithImage(imageUrl, title, message, activityToLaunch,selectedRingtone)

        }



        



    }
}