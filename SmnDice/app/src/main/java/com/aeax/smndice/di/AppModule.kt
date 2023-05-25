package com.aeax.smndice.di

import android.content.Context
import androidx.room.Room
import com.aeax.smndice.dao.PlayerDB
import com.aeax.smndice.domain.repositories.ApiRepository
import com.aeax.smndice.domain.services.implementations.GameManager
import com.aeax.smndice.domain.services.implementations.SmnStoreManager
import com.aeax.smndice.domain.services.interfaces.IApiManager
import com.aeax.smndice.domain.services.interfaces.IGameManager
import com.aeax.smndice.domain.services.interfaces.ISmnStoreManager
import retrofit2.Retrofit.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGameManager(): IGameManager = GameManager()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
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
        apiManager: IApiManager,
        moshi: Moshi
    ) :ApiRepository = ApiRepository(
        apiManager = apiManager,
        moshi = moshi
    )

    @Provides
    @Singleton
    fun providePlayerDao(playerDB: PlayerDB) = playerDB.playerDao()

    @Provides
    @Singleton
    fun providePlayerDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        PlayerDB::class.java,
        "playerDb"
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideSmnStoreManager(@ApplicationContext context: Context) :ISmnStoreManager = SmnStoreManager(context)
}