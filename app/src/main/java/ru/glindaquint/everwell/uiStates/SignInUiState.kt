package ru.glindaquint.everwell.uiStates

data class SignInUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
)
