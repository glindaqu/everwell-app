package ru.glindaquint.everwell.network.dto.users

data class GetUserResponse(
    val email: String,
    val username: String,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val sex: String,
)
