package com.aeax.notificaciontest.ui.screens.camera

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun PhotoMaker(uri: Uri, modifier: Modifier){
    AsyncImage(model = uri, contentDescription = "Foto",modifier=modifier)
}