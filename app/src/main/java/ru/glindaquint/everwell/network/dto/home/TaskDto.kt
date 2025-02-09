package ru.glindaquint.everwell.network.dto.home

data class TaskDto(
    val title: String,
    val deadline: Long,
    val isNotifying: Boolean,
    val isRepeating: Boolean,
    val isCompleted: Boolean,
)
