package com.aeax.notificaciontest.ui.screens.camera

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.aeax.notificaciontest.BuildConfig
import com.aeax.notificaciontest.ui.screens.Screens
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun CameraScreen(onNavigate: (Screens) -> Unit) {
    var display by remember { mutableStateOf("")}
    var uri by remember { mutableStateOf(Uri.EMPTY)}
    val photo by remember { mutableStateOf(true)}
    val context = LocalContext.current

    val photoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        display = if(it) "PHOTO" else ""
    }
    val videoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CaptureVideo()) {
        display = if(it) "VIDEO" else ""
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            if(photo){
                photoLauncher.launch(uri)
            }
            else{
                videoLauncher.launch(uri)
            }
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        if(display == "PHOTO"){
            PhotoMaker(uri = uri, modifier = Modifier.weight(1f))
        }
        else if(display == "VIDEO"){
            VideoMaker(uri = uri)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = {
                display = ""
                val file = context.createFile(false)
                uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID+".provider",file
                )
                val permissionCheckResult = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    photoLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                }

            }){
                Text("Foto")
            }

            Button(onClick = {
                display = ""
                val file = context.createFile(true)
                uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID+".provider",file
                )

                val permissionCheckResult = ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    videoLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                }

            }) {
                Text("Video")
            }
        }

        Button(onClick = {
            onNavigate(Screens.NotificationScreen)
        }) {
            Text("Ir a notificacion")
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun Context.createFile(isVideo: Boolean): File {
    return File.createTempFile(
        "TESTFILE_" + SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + "_",
        if(isVideo) ".mp4" else ".jpg",
        externalCacheDir
    )
}
