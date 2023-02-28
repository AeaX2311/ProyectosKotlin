package com.example.simondice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.ui.theme.*

@Preview
@Composable
fun Preview() {
//    Pad('1', efectoParpadeo = false, esperandoRespuestaJugador = true, accionClick = {})
//    MiBotonColor(true, VerdeOn, VerdeOff,false,{},'1',-45f,-90f
////                        Text(text = "Cadena: $cadenaJuego", color = Color.White)
////                        Text(text = "Respuesta: $respuestaJugador", color = Color.White))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Fondo
    ) {
        Column {
            Box(){
                Text(text = "Nivel 1", color = Color.White, modifier = Modifier.fillMaxWidth().background(
                    FondoSecundario), textAlign = TextAlign.Center, fontSize = 35.sp)
                BotonIcono(
                    habilitado = true,
                    onStart = {
                    },
                    Icons.Filled.Refresh
                )
            }

            Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)
            Pad('1', efectoParpadeo = false, esperandoRespuestaJugador = true, accionClick = {}, Black)
            Divider(startIndent = 8.dp, thickness = 20.dp, color = Fondo)

            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                MiBotonAccion(
                    texto = "Iniciar",
                    true,
                    onStart = {
                    }
                )
            }
        }
    }
}

@Composable
fun BotonIcono(habilitado:Boolean, onStart: () -> Unit, icono: ImageVector) {
    if(habilitado) {
        IconButton(

            enabled = habilitado,
            modifier = Modifier.background(FondoSecundario)
                .size(55.dp),
            onClick = { onStart() }
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun MiBotonAccion(texto:String, habilitado:Boolean, onStart: () -> Unit){
    Button (
        enabled = habilitado,
        onClick = { onStart() },
    ) {
        Text(text = texto)
    }
}

@Composable
fun MiBotonColor(encendido: Boolean, colorOn: Color, colorOff: Color, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit, indice:Char) {
    val intSrc = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
            .border(200.dp, (if (encendido) colorOn else colorOff), RoundedCornerShape(20.dp))
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
}

@Composable
fun Pad(numEncendido:Char, efectoParpadeo:Boolean, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit, padFondo:Color) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.width(218.dp)
                .height(109.dp)
                .background(padFondo),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            MiBotonColor(encendido = numEncendido == '1' && !efectoParpadeo, colorOn = VerdeOn, colorOff = VerdeOff, esperandoRespuestaJugador, accionClick, '1')
            MiBotonColor(encendido = numEncendido == '2' && !efectoParpadeo, colorOn = RojoOn, colorOff = RojoOff, esperandoRespuestaJugador, accionClick, '2')
        }

        Row(
            modifier = Modifier.width(218.dp)
                .height(109.dp)
                .background(padFondo),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            MiBotonColor(encendido = numEncendido == '3' && !efectoParpadeo, colorOn = AmarilloOn, colorOff = AmarilloOff, esperandoRespuestaJugador, accionClick, '3')
            MiBotonColor(encendido = numEncendido == '4' && !efectoParpadeo, colorOn = AzulOn, colorOff = AzulOff, esperandoRespuestaJugador, accionClick, '4')
        }
    }
}