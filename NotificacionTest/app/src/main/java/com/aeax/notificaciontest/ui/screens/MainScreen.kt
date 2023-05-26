package com.aeax.notificaciontest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import com.aeax.notificaciontest.ui.screens.camera.CameraScreen
import com.aeax.notificaciontest.ui.screens.notification.NotificationScreen

sealed class Screens {
    object NotificationScreen : Screens()
    object CameraScreen : Screens()
}

@Composable
fun MainScreen() {

    val currentScreen = remember { mutableStateOf<Screens>(Screens.NotificationScreen) }

    when (currentScreen.value) {
        is Screens.NotificationScreen -> NotificationScreen(onNavigate = { screen -> currentScreen.value = screen })
        is Screens.CameraScreen -> CameraScreen(onNavigate = { screen -> currentScreen.value = screen })
    }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//
//        ) {
//            Button(
//                onClick = {
//                    vm.showNotification(
//                        title = "Notificacion de prueba",
//                        content = "Esto es una notificacion de prueba",
//                    )
//                }
//            ) {
//                Text(text = "Mostrar notificacion")
//            }
//
//            Button(
//                onClick = {
//
//
//                }
//            ) {
//                Text(text = "Camara fotos")
//            }
//
//            Button(
//                onClick = {
//
//                }
//            ) {
//                Text(text = "Camara videos")
//            }
//
//        }
//    }
}