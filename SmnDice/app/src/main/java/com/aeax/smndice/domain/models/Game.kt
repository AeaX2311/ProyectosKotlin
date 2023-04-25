package com.aeax.smndice.domain.models

class Game(
    var playerName: String = "",
    var level: Int = 1,
    var sequence: String = "",
    ) {

    fun startGame() {
        playerName = ""
        level = 1
        sequence = ""
    }
}
