package com.aeax.notificaciontest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen() {
    val vm :MainScreenViewModel = hiltViewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                vm.showNotification(
                    title = "Notificacion de prueba",
                    content = "Esto es una notificacion de prueba",
                )
            }
        ) {
            Text(text = "Mostrar notificacion")
        }
    }
}