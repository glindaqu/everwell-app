package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.network.dto.authorization.SignInResponse

data class SignInUiState(
    val loading: Boolean = true,
    val error: String = "",
    val data: SignInResponse = SignInResponse(),
)
