package ru.glindaquint.everwell.network.dto.authorization

data class UserDto(
    val name: String = "",
    val email: String = "",
    val password: String = "",
)
