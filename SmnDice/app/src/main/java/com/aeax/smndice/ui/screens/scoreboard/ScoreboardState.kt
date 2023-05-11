package com.aeax.smndice.ui.screens.scoreboard

import com.aeax.smndice.domain.models.game.Player

sealed class ScoreboardState {
    object Loading : ScoreboardState()
    data class Success(val scores: List<Player>) : ScoreboardState()
    data class Error(val message: String) : ScoreboardState()
}