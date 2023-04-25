package com.aeax.smndice.di

import com.aeax.smndice.domain.services.implementartions.GameManager
import com.aeax.smndice.domain.services.interfaces.IGameManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameManager(): IGameManager = GameManager()
}