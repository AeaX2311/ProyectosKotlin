package com.aeax.smndice.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aeax.smndice.domain.providers.GlobalProvider

@Composable
fun Navigator(globalProvider: GlobalProvider) {
    NavHost(navController = globalProvider.navController, startDestination = Screens.GameScreen.route) {
        composable(Screens.GameScreen.route) {
            com.aeax.smndice.ui.screens.game.GameScreen()
        }
        composable(Screens.ScoreboardScreen.route) {
            com.aeax.smndice.ui.screens.scoreboard.ScoreboardScreen()
        }
    }
}