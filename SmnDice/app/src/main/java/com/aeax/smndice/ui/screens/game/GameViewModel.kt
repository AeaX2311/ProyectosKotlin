package com.aeax.smndice.ui.screens.game

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeax.smndice.domain.services.interfaces.IGameManager
import com.aeax.smndice.domain.services.interfaces.ISmnStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameManager: IGameManager,
    private val storeManager: ISmnStoreManager
) : ViewModel() {
    private val _uiTheme = MutableStateFlow(false)
    val uiTheme = _uiTheme.asStateFlow()

    init {
        gameManager.startGame()
        getThemeConfig()
    }

    fun getGame() = gameManager.getGame()
    fun startGame() = gameManager.startGame()
    fun levelCompleted() = gameManager.levelCompleted()
    fun levelFailed() = gameManager.levelFailed()
    fun validateAnswer(answer: String) :Boolean{
        return gameManager.validateAnswer(answer)
    }

    private fun getThemeConfig() {
        viewModelScope.launch(Dispatchers.IO) {
            storeManager.getThemeConfig.collect { themeConfig ->
                _uiTheme.value = themeConfig
            }
        }
    }

    fun setThemeConfig(isDarkTheme: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            storeManager.setThemeConfig(isDarkTheme)
        }
    }
}