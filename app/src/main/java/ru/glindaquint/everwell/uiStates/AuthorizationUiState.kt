package ru.glindaquint.everwell.uiStates

data class AuthorizationUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
)
