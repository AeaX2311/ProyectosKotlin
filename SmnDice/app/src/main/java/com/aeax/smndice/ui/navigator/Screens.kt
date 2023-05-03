package com.aeax.smndice.ui.navigator

sealed class Screens(val route: String) {
    object GameScreen : Screens("GameScreen")
    object ScoreboardScreen : Screens("ScoreboardScreen")
}