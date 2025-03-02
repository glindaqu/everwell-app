package ru.glindaquint.everwell.network.dto.authorization

data class RestoreRequest(
    val email: String,
    val password: String,
)
