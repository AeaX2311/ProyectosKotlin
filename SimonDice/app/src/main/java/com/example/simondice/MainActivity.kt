package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.simondice.models.Juego
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val juego = Juego("Alan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var contadorIndice by remember { mutableStateOf(-1) }
            var efectoParpadeo by remember { mutableStateOf(false) }
            var cadenaJuego by remember { mutableStateOf("") }
            var ejecutandoJuego by remember { mutableStateOf(false) }
            var esperandoRespuestaJugador by remember { mutableStateOf(false) }
            var respuestaJugador by remember { mutableStateOf("") }

            SimonDiceTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Fondo
                ) {
                    LaunchedEffect(contadorIndice) {
                        if(ejecutandoJuego) {
                            when(cadenaJuego[contadorIndice]) {
                                '1' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p1))
                                '2' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p2))
                                '3' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p3))
                                '4' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p4))
                                else -> {}
                            }
                            delay(400)
                            efectoParpadeo = true
                            delay(400)

                            efectoParpadeo = false
                            contadorIndice++

                            if(contadorIndice == cadenaJuego.length) { //Ultima iteracion, espera a que e jugador ingrese sus secuencias
                                esperandoRespuestaJugador = true
                                ejecutandoJuego = false
                                contadorIndice = -1
                            }
                        }
                    }

                    Column {

                        Pad(if(contadorIndice != -1) cadenaJuego[contadorIndice] else '0', efectoParpadeo, esperandoRespuestaJugador, accionClick = {
                            //Agregar a la cadena, el ID del boton clickeado
                            respuestaJugador += it

                            if(respuestaJugador.length == cadenaJuego.length) { //Ultima iteracion, el jugador ya no debe ingresar mas cosas
                                //validar si la respuesta es la misma a la cadena original
                                if(respuestaJugador == cadenaJuego) { //Gano
                                    juego.nivelCompletado()
                                    contadorIndice = -1
                                    esperandoRespuestaJugador = false
                                    ejecutandoJuego = false
                                    //TODO Boton start deshabilitado ?
                                } else {
                                    juego.nivelFallado()
                                    contadorIndice = 0 //Volvemos a mostrar el patron
                                    esperandoRespuestaJugador = false
                                    ejecutandoJuego = true
                                }

                                respuestaJugador = ""
                            }
                        })

                        MiBotonAccion (
                            texto = "Iniciar",
                            habilitado = !ejecutandoJuego && !esperandoRespuestaJugador,
                            onStart = {
                                contadorIndice = 0
                                ejecutandoJuego = true
                                esperandoRespuestaJugador = false
                                cadenaJuego = juego.iniciarJuego()
                            }
                        )

                        MiBotonAccion (
                            texto = "Repetir nivel",
                            habilitado = !ejecutandoJuego && esperandoRespuestaJugador,
                            onStart = {
                                contadorIndice = 0
                                ejecutandoJuego = true
                                esperandoRespuestaJugador = false
                                juego.nivelFallado()
                            }
                        )
                        Text(text = "Cadena: $cadenaJuego", color = Color.White)
                        Text(text = "Nivel: " + juego.jugador.nivel, color = Color.White)
                        Text(text = "Puntuacion: " + juego.jugador.puntuacion, color = Color.White)
                        Text(text = "Respuesta: $respuestaJugador", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun MiBotonAccion(texto:String, habilitado:Boolean, onStart: () -> Unit){
    Button (
        enabled = habilitado,
        onClick = { onStart() }) {
        Text(text = texto)
    }
}

@Composable
fun MiBotonColor(encendido: Boolean, colorOn: Color, colorOff:Color, texto:String, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit, indice:Char) {
    val intSrc = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(4.dp)
            .background(if (encendido) colorOn else colorOff)
            .clickable(
                interactionSource = intSrc,
                indication = null
            ) {
                if (!encendido && esperandoRespuestaJugador) {
                    accionClick(indice)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text (
            text = texto,
            fontWeight = FontWeight(900)
        )
    }
}

@Composable
fun Pad(numEncendido:Char, efectoParpadeo:Boolean, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MiBotonColor(encendido = numEncendido == '1' && !efectoParpadeo, colorOn = VerdeOn, colorOff = VerdeOff, texto = "Verde", esperandoRespuestaJugador, accionClick, '1')
            MiBotonColor(encendido = numEncendido == '2' && !efectoParpadeo, colorOn = RojoOn, colorOff = RojoOff, texto = "Rojo", esperandoRespuestaJugador, accionClick, '2')
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MiBotonColor(encendido = numEncendido == '3' && !efectoParpadeo, colorOn = AmarilloOn, colorOff = AmarilloOff, texto = "Amarillo", esperandoRespuestaJugador, accionClick, '3')
            MiBotonColor(encendido = numEncendido == '4' && !efectoParpadeo, colorOn = AzulOn, colorOff = AzulOff, texto = "Azul", esperandoRespuestaJugador, accionClick, '4')
        }
    }
}

private fun iniciarAudio(mp: MediaPlayer) {
    if(!mp.isPlaying) {
        mp.start()
    } else {
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }
}