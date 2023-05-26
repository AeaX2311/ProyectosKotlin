package com.aeax.notificaciontest

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aeax.notificaciontest.ui.screens.MainScreen
import com.aeax.notificaciontest.ui.theme.NotificacionTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificacionTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }

//        requestCameraPermission()
    }

//    private val requestPermissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (isGranted) {
//            Log.d("kilo", "Permission granted")
//        } else {
//            Log.d("kilo", "Permission denied")
//        }
//    }
//
//    private fun requestCameraPermission() {
//        when {
//            ContextCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                Log.i("kilo", "Permission previously granted")
//            }
//
//            ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                android.Manifest.permission.CAMERA
//            ) -> Log.i("kilo", "Show camera permissions dialog")
//
//            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
//        }
//    }
}