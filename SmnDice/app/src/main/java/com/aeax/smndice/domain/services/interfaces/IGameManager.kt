package com.aeax.smndice.domain.services.interfaces

import com.aeax.smndice.domain.models.Game

interface IGameManager {
    fun startGame()
    fun buildSequence()
    fun levelCompleted()
    fun levelFailed()
    fun levelRepeated()
    fun validateAnswer(answer: String): Boolean
    fun getGame(): Game
}
