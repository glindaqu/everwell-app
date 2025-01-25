package ru.glindaquint.everwell.network.dto.authorization

data class SignInRequest(
    val username: String,
    val password: String,
)
