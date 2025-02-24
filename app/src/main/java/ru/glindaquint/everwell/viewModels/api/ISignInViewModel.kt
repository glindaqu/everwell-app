package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.network.dto.authorization.signIn.SignInRequest
import ru.glindaquint.everwell.uiStates.SignInUiState

interface ISignInViewModel {
    fun signIn(request: SignInRequest)

    fun updateUiState(state: SignInUiState)
}
