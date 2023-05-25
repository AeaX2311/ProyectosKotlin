package com.aeax.smndice.domain.services.interfaces

import kotlinx.coroutines.flow.Flow

interface ISmnStoreManager {
    val getThemeConfig: Flow<Boolean>
    suspend fun setThemeConfig(isDarkTheme: Boolean)
    val getUserAccessToken : Flow<String>
    suspend fun setUserAccessToken(userAccessToken: String)
}