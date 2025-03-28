package ru.glindaquint.everwell.network.dto.users

import java.util.Date

data class UpdateProfileRequest(
    val lastname: String?,
    val firstname: String?,
    val patronymic: String?,
    val height: Int?,
    val weight: Int?,
    val birthDate: Date?,
    val sex: String?,
    val diseases: String?,
    val badHabits: List<String>,
)
