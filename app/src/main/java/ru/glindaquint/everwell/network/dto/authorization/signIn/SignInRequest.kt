package ru.glindaquint.everwell.network.dto.authorization.signIn

data class SignInRequest(
    val username: String,
    val password: String,
)
