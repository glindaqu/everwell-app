package ru.glindaquint.everwell.uiStates

data class SignUpUiState(
    val code: String = "",
    val loading: Boolean = false,
    val error: String? = null,
    val successful: Boolean = false,
)
