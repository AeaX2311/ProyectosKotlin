package com.aeax.smndice.ui.screens.game

import androidx.lifecycle.ViewModel
import com.aeax.smndice.domain.services.interfaces.IGameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameManager: IGameManager
) : ViewModel() {

    init {
        gameManager.startGame()
    }

    fun getGame() = gameManager.getGame()

    fun startGame() {
        gameManager.startGame()
    }

    fun buildSequence() {
        gameManager.buildSequence()
    }

    fun levelCompleted() {
        gameManager.levelCompleted()
    }

    fun levelFailed() {
        gameManager.levelFailed()
    }

//    fun levelRepeated() {
//        gameManager.levelRepeated()
//    }

    fun validateAnswer(answer: String) : Boolean {
        return gameManager.validateAnswer(answer)
    }
}