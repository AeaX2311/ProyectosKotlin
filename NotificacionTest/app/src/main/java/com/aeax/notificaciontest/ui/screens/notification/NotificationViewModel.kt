package com.aeax.notificaciontest.ui.screens.notification

import androidx.lifecycle.ViewModel
import com.aeax.notificaciontest.domain.services.interfaces.INotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationManager: INotificationService
) : ViewModel() {

    fun showNotification(title: String, content: String) {
        notificationManager.showNotification(title, content)
    }
}