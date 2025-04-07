package ru.glindaquint.everwell.uiStates

import java.util.Date

data class ProfileUiState(
    val firstname: String = "",
    val lastname: String = "",
    val patronymic: String = "",
    val weight: String = "",
    val height: String = "",
    val badHabits: Set<String> = setOf(),
    val diseases: String = "",
    val sex: String = "",
    val birthDate: Date? = null,
    val username: String = "",
    val error: String? = null,
    val success: Boolean = false,
)
