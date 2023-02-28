package com.example.simondice

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.models.Juego
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay
import com.example.simondice.components.*

class MainActivity : ComponentActivity() {
    private val juego = Juego("Alan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var contadorIndice by remember { mutableStateOf(-1) } //Del patron automatico
            var efectoParpadeo by remember { mutableStateOf(false) } //Boton encendido o apagado

            var ejecutandoJuego by remember { mutableStateOf(false) } //Ejecucion patron automatico
            var esperandoRespuestaJugador by remember { mutableStateOf(false) } //Ejecucion de clicks por usuario

            var cadenaJuego by remember { mutableStateOf("") } //Secuencia String del patron automatico
            var respuestaJugador by remember { mutableStateOf("") } //Secuencia String de clicks por usuario

            var padFondo by remember { mutableStateOf(Black) } //Fondo para el pad, cambia cuando termina un nivel

            SimonDiceTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Fondo
                ) {
                    LaunchedEffect(contadorIndice) {
                        if(ejecutandoJuego) { //Si se esta ejecutando el patron automatico
                            determinarAudioEjecutar(cadenaJuego[contadorIndice], applicationContext) //Ejecuta el sonido del boton
                            delay(400)//Esperate un poco. En este punto el boton esta encendido
                            efectoParpadeo = true //Apaga el boton
                            delay(400) //Dejalo apagado un rato

                            contadorIndice++ //Cambia al siguiente elemento en el patron
                            efectoParpadeo = false //Vuelve a encenderlo

                            if(contadorIndice == cadenaJuego.length) { //Ultima iteracion, espera a que e jugador ingrese sus secuencias
                                ejecutandoJuego = false //No ejecutes el patron
                                esperandoRespuestaJugador = true //Y ahora escucha los clicks
                                contadorIndice = -1 //Resetea el contador del patron, hasta que gane o pierda el nivel
                            }
                        }
                    }

                    LaunchedEffect(respuestaJugador) {
                        if(respuestaJugador.isNotEmpty()) { //Si hay al menos un caracter en la respuesta (clicks)
                            efectoParpadeo = false //Enciende el boton
                            determinarAudioEjecutar(respuestaJugador.last(), applicationContext) //Ejecutar sonido
                            delay(400) //Esperate un rato jeje
                            efectoParpadeo = true //Apaga el boton

                            if(respuestaJugador.length >= cadenaJuego.length) { //Ultima iteracion, el jugador ya no debe ingresar mas cosas
                                esperandoRespuestaJugador = false //Ya no se aceptan clicks
                                ejecutandoJuego = false //Hasta que le de en iniciar
                                efectoParpadeo = false //Cuando inicie la secuencia automatica, se encienda el boton
                                contadorIndice = -1 //El patron hasta que le de click en iniciar

                                //Validar si la respuesta es la misma a la cadena original
                                if(respuestaJugador == cadenaJuego) { //Ganó
                                    juego.nivelCompletado() //Siguiente nivel
                                    padFondo = NivelCompletado //Muestra en verde
                                } else {
                                    juego.nivelFallado() //Reiniciar hasta nivel cero
                                    padFondo = NivelFallido //Muestra en rojo
                                }

                                delay(800) //Esperate un rato para mostrar el color
                                padFondo = Black //Apaga el color
                                respuestaJugador = "" //Termino de dar todos los clicks, reinicia la entrada de clicks
                            }
                        }
                    }

                    Column {
                        Box(){
                            Text(text = "Nivel ${juego.jugador.nivel}", color = Color.White, modifier = Modifier.fillMaxWidth().background(
                                FondoSecundario), textAlign = TextAlign.Center, fontSize = 47.sp)
                            BotonIcono(
                                habilitado = !ejecutandoJuego && esperandoRespuestaJugador,
                                onStart = {
                                    ejecutandoJuego = true
                                    esperandoRespuestaJugador = false
                                    juego.repetirNivel()
                                    contadorIndice = 0
                                },
                                Icons.Filled.Refresh
                            )
                        }

                        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)
                        Pad(if(contadorIndice != -1) cadenaJuego[contadorIndice] else if(respuestaJugador.isNotEmpty()) respuestaJugador.last() else '0', efectoParpadeo, esperandoRespuestaJugador, accionClick = {
                            respuestaJugador += it //Agregar a la cadena, el ID del boton clickeado
                        },padFondo)
                        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            MiBotonAccion (
                                texto = "Iniciar",
                                habilitado = !ejecutandoJuego && !esperandoRespuestaJugador,
                                onStart = {
                                    ejecutandoJuego = true
                                    esperandoRespuestaJugador = false
                                    cadenaJuego = juego.iniciarJuego()
                                    contadorIndice = 0
                                }
                            )
                        }

                        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)
                        Text(text = "" + juego.jugador.puntuacion + " Puntos", color = Color.White, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 35.sp)
                        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)
                    }
                }
            }
        }
    }
}

/**
 * Ejecuta un audio en base al boton que se le de click o se este ejecutando
 */
private fun determinarAudioEjecutar(indice:Char, applicationContext: Context) {
    when(indice) { //Ejecuta la musica
        '1' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p1))
        '2' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p2))
        '3' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p3))
        '4' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p4))
        else -> {}
    }
}

/**
 * Ejecuta una salida de audio, evitando que se sobrecargue cuando se ejecuta multiples veces
 */
private fun iniciarAudio(mp: MediaPlayer) {
    if(!mp.isPlaying) {
        mp.start()
    } else { //Detener la ejecucion actual
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }
}