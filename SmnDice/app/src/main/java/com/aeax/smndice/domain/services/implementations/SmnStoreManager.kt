package com.aeax.smndice.domain.services.implementations

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aeax.smndice.domain.services.interfaces.ISmnStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SmnStoreManager (private val context: Context) :ISmnStoreManager {
    companion object {
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("settings")
        private val THEME_CONFIG = booleanPreferencesKey("theme_config")
        private val USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
    }

    override val getThemeConfig: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[THEME_CONFIG] ?: false
    }

    override suspend fun setThemeConfig(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_CONFIG] = isDarkTheme
        }
    }

    override val getUserAccessToken : Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_ACCESS_TOKEN] ?: ""
    }

    override suspend fun setUserAccessToken(userAccessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ACCESS_TOKEN] = userAccessToken
        }
    }
}