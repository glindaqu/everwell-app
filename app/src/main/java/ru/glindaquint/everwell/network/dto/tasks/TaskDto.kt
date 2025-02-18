package ru.glindaquint.everwell.network.dto.tasks

import java.util.Date

data class TaskDto(
    val taskId: Long,
    val title: String,
    val description: String,
    val creationDate: Date,
    val lastChangeDate: Date,
    val ownerId: Long,
    val repeat: String,
    val isNotificationEnabled: Boolean,
    val deadlineDate: Date,
    val isCompleted: Boolean,
)
