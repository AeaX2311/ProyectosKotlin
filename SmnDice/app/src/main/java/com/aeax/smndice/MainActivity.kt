package com.aeax.smndice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aeax.smndice.domain.providers.GlobalProvider
import com.aeax.smndice.domain.providers.LocalGlobalProvider
import com.aeax.smndice.domain.repositories.ApiRepository
import com.aeax.smndice.ui.navigator.Navigator
import com.aeax.smndice.ui.theme.Fondo
import com.aeax.smndice.ui.theme.SmnDiceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gp = GlobalProvider(navController = rememberNavController())

            SmnDiceTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Fondo
                    ) {
                        Navigator(gp)
                    }
                }
            }
        }
    }
}