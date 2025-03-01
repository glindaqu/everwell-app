package ru.glindaquint.everwell.uiStates

data class RestoreUiState(
    val code: String = "",
    val error: String? = null,
    val loading: Boolean = false,
)
