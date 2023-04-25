package com.aeax.smndice.domain.providers

import androidx.compose.runtime.compositionLocalOf
import com.aeax.smndice.ui.screens.game.GameViewModel

data class GlobalProvider (
    val gameViewModel: GameViewModel,

)

val LocalGlobalProvider = compositionLocalOf<GlobalProvider> {
    error("No GlobalProvider provided")
}