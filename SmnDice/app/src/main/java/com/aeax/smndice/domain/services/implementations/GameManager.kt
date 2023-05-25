package com.aeax.smndice.domain.services.implementations

import com.aeax.smndice.domain.models.game.Game
import com.aeax.smndice.domain.services.interfaces.IGameManager
import kotlin.random.Random

class GameManager : IGameManager {
    //Constantes sobre las reglas de mi juego
    private val cantidadTotalBotones = 4
    private var game = Game("", 1, "")

    override fun initGame() {
        game = Game("", 1, "")
    }

    /**
     * Inicializa el juego entero. Score y nivel 0.
     */
    override fun startGame() {
        //game = Game("", 1, "")
//        game.startGame()
        buildSequence()
    }

    /**
     * Genera una cadena de n caracteres segun el nivel del jugador
     * Cada caracter representa un estado de encendido para el boton con ese id
     */
    override fun buildSequence() {
        game.sequence += Random.nextInt(1, cantidadTotalBotones + 1).toString()
    }

    /**
     * Se ejecuta cuando un nivel se finaliza con exito
     */
    override fun levelCompleted() {
        game.level++
    }

    override fun validateAnswer(answer: String): Boolean {
        //Por cada caracter de la respuesta, se compara con el caracter de la secuencia
        //Si al menos uno no es igual, retorna falso
        for (i in answer.indices) {
            if (answer[i] != game.sequence[i]) {
                return false
            }
        }

        return true
    }

    /**
     * Se ejecuta cuando un nivel se finaliza sin exito
     */
    override fun levelFailed() {
        game.startGame()
    }

    /**
     * El usuario le da click al boton de repetir nivel, se le quitan 5 puntos
     */
    override fun levelRepeated() {
        //TODO Ver si se va a mantener
    }

    override fun getGame() = game
}