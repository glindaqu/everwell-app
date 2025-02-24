package ru.glindaquint.everwell.network.dto.authorization.signUp

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
)
