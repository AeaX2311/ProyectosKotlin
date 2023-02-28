package com.example.simondice.models

import kotlin.random.Random

class Juego(nombre: String) {
    //Constantes sobre las reglas de mi juego
    private val cantidadTotalBotones = 4

    //Variables que influyen en el juego
    //TODO: POR AHORA EL JUGADOR ES GLOBAL. SOLO POR PRUEBAS
     var jugador :Jugador = Jugador("Desconocido", 0, 0)

    /**
     * Inicializa el juego entero. Score y nivel 0.
//     * @param nombre El nombre del jugador que inicia el juego
     */
    fun iniciarJuego() :String {
        return generarCadenaJuego()
    }

    /**
     * Genera una cadena de n caracteres segun el nivel del jugador
     * Cada caracter representa un estado de encendido para el boton con ese id
     * @return cadena generada
     */
    private fun generarCadenaJuego() :String {
        var cadenaJuego = ""
        val repeticionesSegunNivel = determinarRepeticionesJuego()

        for(x in 1 .. repeticionesSegunNivel) {
            //Agrega un aleatorio entre 1 y la cantidad de botones que tengamos
            cadenaJuego += Random.nextInt(1, cantidadTotalBotones + 1)
        }

        return cadenaJuego
    }

    /**
     * Determina cuantas repeticiones (botones encendidos) se realizaran en el juego
     * Se basa en el nivel del jugador
     * @return cantidad de repeticiones
     */
    private fun determinarRepeticionesJuego() :Int {
        var repeticionesSegunNivel = 4

        if(jugador.nivel > 3) repeticionesSegunNivel++
        if(jugador.nivel > 5) repeticionesSegunNivel++
        if(jugador.nivel > 7) repeticionesSegunNivel++

        return repeticionesSegunNivel
    }

    /**
     * Se ejecuta cuando un nivel se finaliza con exito
     */
    fun nivelCompletado() {
        //Subimos de nivel
        jugador.nivel++
        //Por ahora, sera un score random.
            //TODO: Score en base al tiempo tardado
        jugador.puntuacion += Random.nextInt(10,20)
    }

    /**
     * Se ejecuta cuando un nivel se finaliza sin exito
     */
    fun nivelFallado() {
        //Reiniciar a nivel cero
        jugador.puntuacion = 0
        jugador.nivel = 1
    }

    /**
     * El usuario le da click al boton de repetir nivel, se le quitan 5 puntos
     */
    fun repetirNivel() {
        jugador.puntuacion -= 5
    }
}