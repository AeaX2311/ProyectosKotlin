package com.aeax.notificaciontest.di

import android.content.Context
import androidx.core.app.NotificationCompat
import com.aeax.notificaciontest.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideNotificationBuilder(@ApplicationContext context :Context) =
//        NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.notification_icon)
//            .setContentTitle("Notificacion prueba")
//            .setContentText("Esto es una prueba de notificacion")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
}
