package com.aeax.smndice.di

import com.aeax.smndice.domain.repositories.ApiRepository
import com.aeax.smndice.domain.services.implementartions.GameManager
import com.aeax.smndice.domain.services.interfaces.IApiManager
import com.aeax.smndice.domain.services.interfaces.IGameManager
import retrofit2.Retrofit.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameManager(): IGameManager = GameManager()

    @Provides @Singleton fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideApiManager(moshi: Moshi): IApiManager =
        Builder().run {
            baseUrl(IApiManager.BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        }.create(IApiManager::class.java)

    @Provides
    @Singleton
    fun provideApiRepository(
        apiManager: IApiManager
    ) :ApiRepository = ApiRepository(
        apiManager = apiManager
    )
}