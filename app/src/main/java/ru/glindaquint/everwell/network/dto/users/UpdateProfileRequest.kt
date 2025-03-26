package ru.glindaquint.everwell.network.dto.users

import java.time.LocalDate

data class UpdateProfileRequest(
    val lastname: String?,
    val firstname: String?,
    val patronymic: String?,
    val height: Int?,
    val weight: Int?,
    val birthDate: LocalDate?,
    val sex: String?,
    val diseases: String?,
    val badHabits: List<String>,
)
