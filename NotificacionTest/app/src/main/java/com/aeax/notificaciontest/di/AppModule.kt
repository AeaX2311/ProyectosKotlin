package com.aeax.notificaciontest.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.aeax.notificaciontest.domain.services.implementations.NotificationService
import com.aeax.notificaciontest.domain.services.interfaces.INotificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @RequiresApi(Build.VERSION_CODES.O)
    fun provideNotificationManager(@ApplicationContext context: Context): INotificationService =
        NotificationService(context)
}
