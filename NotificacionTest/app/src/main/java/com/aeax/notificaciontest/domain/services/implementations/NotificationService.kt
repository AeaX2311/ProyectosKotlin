package com.aeax.notificaciontest.domain.services.implementations

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.aeax.notificaciontest.domain.constants.NotificationConstants
import com.aeax.notificaciontest.domain.models.MyActionReceiver
import com.aeax.notificaciontest.domain.services.interfaces.INotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@RequiresApi(Build.VERSION_CODES.O)
class NotificationService @Inject constructor(
    @ApplicationContext private val context: Context,
) :INotificationService {
    init {
        createNotificationChannel()
    }

    @SuppressLint("ServiceCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationConstants.CHANNEL_ID,
            NotificationConstants.CHANNEL_NAME,
            NotificationConstants.IMPORTANCE_LEVEL
        )
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("PrivateResource", "UnspecifiedImmutableFlag")
    override fun showNotification(title: String, content: String) {
        val intent = Intent(context, MyActionReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val notificationBuilder = NotificationCompat.Builder(context, NotificationConstants.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(androidx.core.R.drawable.ic_call_answer)
            .setAutoCancel(true)
            .addAction(androidx.core.R.drawable.ic_call_answer, "Dame click jiji", pendingIntent)

        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as android.app.NotificationManager
        notificationManager.notify(NotificationConstants.NOTIFICATION_ID, notificationBuilder.build())
    }
}