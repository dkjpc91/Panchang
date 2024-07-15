package com.mithilakshar.mithilapanchang.Utility


import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.RemoteViews
import com.mithilakshar.mithilapanchang.R
import java.text.SimpleDateFormat
import java.util.*

class DateWidgetProvider : AppWidgetProvider() {

    private var mediaPlayer: MediaPlayer? = null
    private var clickCount = 0

    companion object {
        private const val WIDGET_IMAGE_CLICK = "com.mithilakshar.mithilapanchang.Utility.WIDGET_IMAGE_CLICK"

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.date_widget)

            // Set current date
            val currentDate = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault()).format(Date())
            views.setTextViewText(R.id.widgettext, currentDate)

            // Set up music play button
            val intentMusic = Intent(context, DateWidgetProvider::class.java)
            intentMusic.action = WIDGET_IMAGE_CLICK
            val pendingIntentMusic = PendingIntent.getBroadcast(
                context,
                appWidgetId,
                intentMusic,
                PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE if it fits your use case
            )
            views.setOnClickPendingIntent(R.id.widgetimage, pendingIntentMusic)

            // Update the widget view
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == WIDGET_IMAGE_CLICK) {
            clickCount++

            // Handle music playback
            context?.let { ctx ->
                if (mediaPlayer != null) {
                    mediaPlayer?.stop()
                    mediaPlayer?.release()
                    mediaPlayer = null
                }

                if (clickCount % 2 == 1) {
                    // Play music on odd clicks (1st, 3rd, etc.)
                    mediaPlayer = MediaPlayer.create(ctx, R.raw.maithili)
                    mediaPlayer?.start()
                }
            }
        }
    }


    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
