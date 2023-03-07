package com.aeax.gato.components

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GatoViewModel : ViewModel() {

    private val _gato = MutableStateFlow(GatoState())
    val gato = _gato.asStateFlow()


    init {
        _gato.value = GatoState(
            gatoArray = _gato.value.gatoArray,
            isDraw = false,
            player = "X",
            winner = null,
            isGameOver = false
        )
    }



    fun colocarValorJugadorAGatoArray(fila: Int, columna: Int) {
        //Change player if X then put O and viceversa
      _gato.value = GatoState(
            gatoArray = _gato.value.gatoArray.apply {
                this[fila][columna] = _gato.value.player
            },
            isDraw = false,
            player = if (_gato.value.player == "X") "O" else "X",
            winner = null,
            isGameOver = false
        )
    }

    fun resetGame(){
        _gato.value = GatoState(
            gatoArray = arrayOf( arrayOf("","",""), arrayOf("","",""), arrayOf("","","")),
            isDraw = false,
            player = "X",
            winner = null,
            isGameOver = false
        )
    }


    fun determinarStatusJuego(): String {
        var resultado: String = ""
        var cadena = ""
        var gato = _gato.value.gatoArray

        //Si hay ganador horizontal
        //recorrer las filas de gato
        for (fila in gato) {
            //recorrer las columnas de gato
            for (columna in fila) {
                //concatenar el valor de la columna
                cadena += columna
            }
            //verificar si la cadena es ganadora
            resultado = verificarGanadorSegunCadena(cadena) ?: ""

            //si hay ganador, retornar el resultado
            if (resultado != "") {
                return imprimirResultado(resultado)
            }
            //limpiar la cadena
            cadena = ""
        }
        //Si hay ganador vertical
        //recorrer las columnas de gato
        for (columna in 0..2) {
            //recorrer las filas de gato
            for (fila in 0..2) {
                //concatenar el valor de la columna
                cadena += gato[fila][columna]
            }
            //verificar si la cadena es ganadora
            resultado = verificarGanadorSegunCadena(cadena) ?: ""

            //si hay ganador, retornar el resultado
            if (resultado != "") {
                return imprimirResultado(resultado)
            }
            //limpiar la cadena
            cadena = ""
        }
        //Si hay ganador diagonal
        //recorrer la diagonal 1 del gato
        for (fila in 0..2) {
            //concatenar el valor de la columna
            cadena += gato[fila][fila]
        }
        //verificar si la cadena es ganadora
        resultado = verificarGanadorSegunCadena(cadena) ?: ""

        //si hay ganador, retornar el resultado
        if (resultado != "") {
            return imprimirResultado(resultado)
        }
        //limpiar la cadena
        cadena = ""
        //recorrer la diagonal 2 del gato
        for (fila in 0..2) {
            //concatenar el valor de la columna
            cadena += gato[fila][2 - fila]
        }
        //verificar si la cadena es ganadora
        resultado = verificarGanadorSegunCadena(cadena) ?: ""

        //si hay ganador, retornar el resultado
        if (resultado != "") {
            return imprimirResultado(resultado)
        }
        //Si todas las casillas estan llenas y no hay ganador
        var gatoLleno = true
        //recorrer las filas de gato
        for (fila in gato) {
            //recorrer las columnas de gato
            for (columna in fila) {
                //si hay una casilla vacia, el gato no esta lleno, y se puede seguir jugando
                if (columna == "") {
                    return ""
                }
            }
        }
        //Si el gato ya se lleno, es que hay un empate
        return "empate"
    }


    private fun verificarGanadorSegunCadena(cadena: String): String {
        return when (cadena) {
            "XXX" -> {
                "X"
            }
            "OOO" -> {
                "O"
            }
            else -> {
                ""
            }
        }
    }

    private fun imprimirResultado(ganador: String): String {
        return "Ganador: $ganador"
    }

}