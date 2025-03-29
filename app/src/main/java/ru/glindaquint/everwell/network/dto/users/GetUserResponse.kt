package ru.glindaquint.everwell.network.dto.users

import java.util.Date

data class GetUserResponse(
    val email: String,
    val username: String,
    val firstname: String?,
    val lastname: String?,
    val patronymic: String?,
    val sex: String?,
    val birthDate: Date?,
    val diseases: String?,
    val badHabits: Set<String>,
    val weight: Int?,
    val height: Int?,
)
