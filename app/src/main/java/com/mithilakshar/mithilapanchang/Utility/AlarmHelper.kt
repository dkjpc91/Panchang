    package com.mithilakshar.mithilapanchang.Utility

    import android.app.AlarmManager
    import android.app.PendingIntent
    import android.content.Context
    import android.content.Intent
    import android.content.SharedPreferences
    import java.util.Calendar


    class AlarmHelper(val context: Context) {

        private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        fun setAlarm(calendar: Calendar,  title: String, message: String) {
            val futureInMillis = calendar.timeInMillis
            val intent = createAlarmIntent(title,message)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent)
        }

        fun cancelAlarm(title: String, message: String) {
            val intent = createAlarmIntent(title, message)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE)
            pendingIntent?.let { alarmManager.cancel(it) }
        }


        private fun createAlarmIntent(title: String, message: String): Intent {
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.action = "com.example.app.ALARM_ACTION" // Replace with your desired action
            intent.putExtra("title", title)
            intent.putExtra("message", message) // Add any additional data here
            return intent
        }
    }