package com.aeax.notificaciontest.ui.screens.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.aeax.notificaciontest.ui.screens.Screens

@Composable
fun NotificationScreen(onNavigate: (Screens) -> Unit) {
    val vm : NotificationViewModel = hiltViewModel()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
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

            Button(
                onClick = {
                    onNavigate(Screens.CameraScreen)
                }
            ) {
                Text(text = "Camara fotos")
            }
        }
    }
}