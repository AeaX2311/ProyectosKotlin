package com.aeax.smndice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aeax.smndice.ui.theme.*

@Composable
fun Pad(numEncendido:Char, efectoParpadeo:Boolean, esperandoRespuestaJugador:Boolean, accionClick: (indice:Char) -> Unit, padFondo: Color) {
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