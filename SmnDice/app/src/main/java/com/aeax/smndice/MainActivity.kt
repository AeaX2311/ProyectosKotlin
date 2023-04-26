package com.aeax.smndice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.aeax.smndice.domain.providers.GlobalProvider
import com.aeax.smndice.domain.providers.LocalGlobalProvider
import com.aeax.smndice.ui.screens.game.GameViewModel
import com.aeax.smndice.ui.screens.game.SmnDiceGameScreen
import com.aeax.smndice.ui.theme.Fondo
import com.aeax.smndice.ui.theme.SmnDiceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val gp = GlobalProvider(gameViewModel = gameViewModel)

            SmnDiceTheme {
//                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Fondo
                    ) {
                        SmnDiceGameScreen()
                    }
//                }
            }
        }
    }
}