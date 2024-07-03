    package com.mithilakshar.mithilapanchang.Utility

    import android.app.AlarmManager
    import android.app.PendingIntent
    import android.content.Context
    import android.content.Intent
    import android.content.SharedPreferences
    import android.widget.Toast
    import java.util.Calendar


    class AlarmHelper(val context: Context) {

        private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        fun setAlarm(calendar: Calendar,  title: String, message: String,selectedRingtone: Int) {
            val futureInMillis = calendar.timeInMillis
            val intent = createAlarmIntent(title,message,selectedRingtone)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_MUTABLE)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent)
        }

        fun cancelAlarm(title: String, message: String,selectedRingtone: Int) {
            fun cancelAlarm(title: String, message: String, selectedRingtone: Int) {
                val intent = createAlarmIntent(title, message, selectedRingtone)
                val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE)
                pendingIntent?.let { alarmManager.cancel(it) } // Only cancel if pendingIntent exists
            }
        }


        private fun createAlarmIntent(title: String, message: String,selectedRingtone: Int): Intent {
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.action = "com.example.app.ALARM_ACTION" // Replace with your desired action
            intent.putExtra("title", title)
            intent.putExtra("selectedRingtone",selectedRingtone)
            intent.putExtra("message", message) // Add any additional data here
            return intent
        }
    }