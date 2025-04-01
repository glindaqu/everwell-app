package ru.glindaquint.everwell.network.dto.notifications

import java.util.Date

data class NotificationDto(
    val category: String,
    val title: String,
    val description: String,
    val receivingDateTime: Date,
    val repeatType: String,
    val isRead: Boolean,
)
