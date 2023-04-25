package com.aeax.smndice.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.aeax.smndice.ui.theme.FondoSecundario

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

