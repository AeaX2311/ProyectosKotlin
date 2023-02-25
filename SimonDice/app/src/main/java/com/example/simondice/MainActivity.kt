package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import com.example.simondice.models.Juego
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay

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

            SimonDiceTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Fondo
                ) {
                    LaunchedEffect(contadorIndice) {
                        if(ejecutandoJuego) { //Si se esta ejecutando el patron automatico
                            when(cadenaJuego[contadorIndice]) { //Ejecuta el sonido del boton
                                '1' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p1))
                                '2' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p2))
                                '3' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p3))
                                '4' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p4))
                                else -> {}
                            }
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

                            when(respuestaJugador.last()) { //Ejecuta la musica
                                '1' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p1))
                                '2' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p2))
                                '3' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p3))
                                '4' -> iniciarAudio(MediaPlayer.create(applicationContext, R.raw.p4))
                                else -> {}
                            }
                            delay(400) //Esperate un rato jeje
                            efectoParpadeo = true //Apaga el boton

                            if(respuestaJugador.length == cadenaJuego.length) { //Ultima iteracion, el jugador ya no debe ingresar mas cosas
                                esperandoRespuestaJugador = false //Ya no se aceptan clicks

                                //validar si la respuesta es la misma a la cadena original
                                if(respuestaJugador == cadenaJuego) { //Ganó
                                    juego.nivelCompletado() //Siguiente nivel
                                    ejecutandoJuego = false //Hasta que le de en iniciar
                                    efectoParpadeo = false //Cuando inicie la secuencia automatica, se encienda el boton
                                    contadorIndice = -1 //El patron hasta que le de click en iniciar
                                } else {
                                    juego.nivelFallado() //Repetir nivel
                                    ejecutandoJuego = true //Repetir patron
                                    delay(1500) //Esperate un rato, que se de cuenta que perdio
                                    efectoParpadeo = false //Cuando inicie la secuencia automatica, se encienda el boton
                                    contadorIndice = 0 //Volvemos a mostrar el patron
                                }

                                respuestaJugador = "" //Termino de dar todos los clicks, reinicia la entrada de clicks
                            }
                        }
                    }

                    Column {

                        Pad(if(contadorIndice != -1) cadenaJuego[contadorIndice] else if(respuestaJugador.isNotEmpty()) respuestaJugador.last() else '0', efectoParpadeo, esperandoRespuestaJugador, accionClick = {
                            respuestaJugador += it //Agregar a la cadena, el ID del boton clickeado
                        })

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

                        MiBotonAccion (
                            texto = "Repetir nivel",
                            habilitado = !ejecutandoJuego && esperandoRespuestaJugador,
                            onStart = {
                                ejecutandoJuego = true
                                esperandoRespuestaJugador = false
                                juego.nivelFallado()
                                contadorIndice = 0
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

@Preview
@Composable
fun Preview() {
//    Pad('1', efectoParpadeo = false, esperandoRespuestaJugador = true, accionClick = {})
    MiBotonColor(true, VerdeOn, VerdeOff,false,{},'1',-45f,-90f)
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
fun MiBotonColor(encendido: Boolean, colorOn: Color, colorOff:Color, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit, indice:Char, startAngle:Float, sweepAngle:Float) {
    val intSrc = remember { MutableInteractionSource() }


    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(4.dp)
            .background(if (encendido) colorOn else colorOff)

//            .rotate(90f)
            .clickable(
                interactionSource = intSrc,
                indication = null
            ) {
                if (!encendido && esperandoRespuestaJugador) {
                    accionClick(indice)
                }
            },
        contentAlignment = Alignment.Center,

    ) {
    }
//    Canvas(
//        modifier = Modifier
//            .size(size = 250.dp)
//            .padding(5.dp)
//            .clickable(
//                interactionSource = intSrc,
//                indication = null
//            ) {
//                if (!encendido && esperandoRespuestaJugador) {
//                    accionClick(indice)
//                }
//            },
//    ) {
//        drawArc(
//            color = if (encendido) colorOn else colorOff,
//            startAngle = startAngle,
//            sweepAngle = sweepAngle,
//            useCenter = true,
//        )
//    }
}

//@Composable
//fun Pad(numEncendido:Char, efectoParpadeo:Boolean, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit) {
//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Box(
//            modifier = Modifier.fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            MiBotonColor(encendido = numEncendido == '1' && !efectoParpadeo, colorOn = VerdeOn, colorOff = VerdeOff, esperandoRespuestaJugador, accionClick, '1',-45f,-90f)
//            MiBotonColor(encendido = numEncendido == '2' && !efectoParpadeo, colorOn = AmarilloOn, colorOff = AmarilloOff, esperandoRespuestaJugador, accionClick, '2',-225f,90f)
//            MiBotonColor(encendido = numEncendido == '3' && !efectoParpadeo, colorOn = RojoOn, colorOff = RojoOff, esperandoRespuestaJugador, accionClick, '3',-45f,90f)
//            MiBotonColor(encendido = numEncendido == '4' && !efectoParpadeo, colorOn = AzulOn, colorOff = AzulOff, esperandoRespuestaJugador, accionClick, '4',-225f,-90f)
//        }
//    }
//}

@Composable
fun Pad(numEncendido:Char, efectoParpadeo:Boolean, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MiBotonColor(encendido = numEncendido == '1' && !efectoParpadeo, colorOn = VerdeOn, colorOff = VerdeOff, esperandoRespuestaJugador, accionClick, '1',0f,0f)
            MiBotonColor(encendido = numEncendido == '2' && !efectoParpadeo, colorOn = RojoOn, colorOff = RojoOff, esperandoRespuestaJugador, accionClick, '2',0f,0f)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            MiBotonColor(encendido = numEncendido == '3' && !efectoParpadeo, colorOn = AmarilloOn, colorOff = AmarilloOff, esperandoRespuestaJugador, accionClick, '3',0f,0f)
            MiBotonColor(encendido = numEncendido == '4' && !efectoParpadeo, colorOn = AzulOn, colorOff = AzulOff, esperandoRespuestaJugador, accionClick, '4',0f,0f)
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