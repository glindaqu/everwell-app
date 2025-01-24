package ru.glindaquint.everwell.uiStates

data class SignInUiState(
    val loading: Boolean = true,
    val error: String,
    val data: Any,
)
