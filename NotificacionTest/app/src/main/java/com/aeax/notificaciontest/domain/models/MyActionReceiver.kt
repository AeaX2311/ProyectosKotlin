package com.aeax.notificaciontest.domain.models

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Se hizo clic en el botón de la notificación", Toast.LENGTH_SHORT).show()
    }
}