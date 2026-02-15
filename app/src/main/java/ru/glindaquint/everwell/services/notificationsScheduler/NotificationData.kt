package ru.glindaquint.everwell.services.notificationsScheduler

import android.os.Bundle
import androidx.core.app.NotificationCompat

data class NotificationData(
    val title: String,
    val message: String,
    val channelId: String,
    val smallIcon: Int,
    val priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    val extras: Bundle = Bundle.EMPTY,
    val autoCancel: Boolean = true,
)
