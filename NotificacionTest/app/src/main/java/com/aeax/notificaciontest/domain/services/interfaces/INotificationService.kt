package com.aeax.notificaciontest.domain.services.interfaces

interface INotificationService {
    fun createNotificationChannel()
    fun showNotification(title: String, content: String)
}