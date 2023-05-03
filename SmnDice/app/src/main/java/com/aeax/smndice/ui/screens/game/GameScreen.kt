package com.aeax.smndice.ui.screens.game

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aeax.smndice.R
import com.aeax.smndice.domain.providers.LocalGlobalProvider
import com.aeax.smndice.ui.components.*
import com.aeax.smndice.ui.navigator.Screens
import com.aeax.smndice.ui.screens.scoreboard.ScoreboardViewModel
import com.aeax.smndice.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun GameScreen() {
    val gameViewModel: GameViewModel = hiltViewModel()

    val context = LocalContext.current
    val gp = LocalGlobalProvider.current
    val navController = gp.navController

    val currentGame by remember { mutableStateOf(gameViewModel.getGame()) } //Datos del juego actual
    var contadorIndice by remember { mutableStateOf(-1) } //Del patron automatico
    var efectoParpadeo by remember { mutableStateOf(false) } //Boton encendido o apagado
    var ejecutandoJuego by remember { mutableStateOf(false) } //Ejecucion patron automatico
    var esperandoRespuestaJugador by remember { mutableStateOf(false) } //Ejecucion de clicks por usuario
    var respuestaJugador by remember { mutableStateOf("") } //Secuencia String de clicks por usuario
    var padFondo by remember { mutableStateOf(Black) } //Fondo para el pad, cambia cuando termina un nivel

    LaunchedEffect(contadorIndice) {
        if (ejecutandoJuego) { //Si se esta ejecutando el patron automatico
            determinarAudioEjecutar(
                currentGame.sequence[contadorIndice],
                context
            ) //Ejecuta el sonido del boton
            delay(400)//Esperate un poco. En este punto el boton esta encendido
            efectoParpadeo = true //Apaga el boton
            delay(400) //Dejalo apagado un rato

            contadorIndice++ //Cambia al siguiente elemento en el patron
            efectoParpadeo = false //Vuelve a encenderlo

            if (contadorIndice == currentGame.level) { //Ultima iteracion, espera a que e jugador ingrese sus secuencias
                ejecutandoJuego = false //No ejecutes el patron
                esperandoRespuestaJugador = true //Y ahora escucha los clicks
                contadorIndice = -1 //Resetea el contador del patron, hasta que gane o pierda el nivel
            }
        }
    }

    LaunchedEffect(respuestaJugador) {
        if (respuestaJugador.isNotEmpty()) { //Si hay al menos un caracter en la respuesta (clicks)
            efectoParpadeo = false //Enciende el boton
            determinarAudioEjecutar(
                respuestaJugador.last(),
                context
            ) //Ejecutar sonido
            delay(400) //Esperate un rato jeje
            efectoParpadeo = true //Apaga el boton

            if (currentGame.level == respuestaJugador.length) { //Ultima iteracion, el jugador ya no debe ingresar mas cosas
                esperandoRespuestaJugador = false //Ya no se aceptan clicks
                ejecutandoJuego = false //Hasta que le de en iniciar
                efectoParpadeo = false //Cuando inicie la secuencia automatica, se encienda el boton
                contadorIndice = -1 //El patron hasta que le de click en iniciar

                //Validar si la respuesta es la misma a la cadena original
                if (gameViewModel.validateAnswer(respuestaJugador)) { //Ganó
                    padFondo = NivelCompletado //Muestra en verde
                    gameViewModel.levelCompleted()
                } else {
                    padFondo = NivelFallido //Muestra en rojo
                    gameViewModel.levelFailed()
                }

                delay(800) //Esperate un rato para mostrar el color
                padFondo = Black //Apaga el color
                respuestaJugador = "" //Termino de dar todos los clicks, reinicia la entrada de clicks
            } else if (!gameViewModel.validateAnswer(respuestaJugador)) {
                esperandoRespuestaJugador = false //Ya no se aceptan clicks
                ejecutandoJuego = false //Hasta que le de en iniciar
                efectoParpadeo = false //Cuando inicie la secuencia automatica, se encienda el boton
                contadorIndice = -1 //El patron hasta que le de click en iniciar
                padFondo = NivelFallido //Muestra en rojo
                gameViewModel.levelFailed()
                delay(800) //Esperate un rato para mostrar el color
                padFondo = Black //Apaga el color
                respuestaJugador = "" //Termino de dar todos los clicks, reinicia la entrada de clicks
            }
        }
    }

    Column {
        Box {
            Text(
                text = "Nivel ${currentGame.level}",
                color = Color.White,
                modifier = Modifier.fillMaxWidth().background(
                    FondoSecundario
                ),
                textAlign = TextAlign.Center,
                fontSize = 47.sp
            )
            BotonIcono(
                habilitado = !ejecutandoJuego && esperandoRespuestaJugador,
                onStart = {
                    ejecutandoJuego = true
                    esperandoRespuestaJugador = false
                    //gameViewModel.levelRepeated()
                    contadorIndice = 0
                },
                Icons.Filled.Refresh
            )
        }

        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)
        Pad(
            if (contadorIndice != -1) currentGame.sequence[contadorIndice] else if (respuestaJugador.isNotEmpty()) respuestaJugador.last() else '0',
            efectoParpadeo,
            esperandoRespuestaJugador,
            accionClick = {
                respuestaJugador += it //Agregar a la cadena, el ID del boton clickeado
            },
            padFondo
        )
        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            MiBotonAccion(
                texto = "Iniciar",
                habilitado = !ejecutandoJuego && !esperandoRespuestaJugador,
                onStart = {
                    ejecutandoJuego = true
                    esperandoRespuestaJugador = false
                    gameViewModel.startGame()
                    contadorIndice = 0
                }
            )
        }

        Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            MiBotonAccion(
                texto = "Lista de scores",
                habilitado = !ejecutandoJuego && !esperandoRespuestaJugador,
                onStart = {
                    navController.navigate(Screens.ScoreboardScreen.route)
                }
            )
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