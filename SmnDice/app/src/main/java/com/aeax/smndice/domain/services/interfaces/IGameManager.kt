package com.aeax.smndice.domain.services.interfaces

import com.aeax.smndice.domain.models.game.Game

interface IGameManager {
    fun initGame()
    fun startGame()
    fun buildSequence()
    fun levelCompleted()
    fun levelFailed()
    fun levelRepeated()
    fun validateAnswer(answer: String): Boolean
    fun getGame(): Game
}
