package ru.glindaquint.everwell.viewModels.api

import retrofit2.Call
import ru.glindaquint.everwell.uiStates.SignInUiState

interface ISignInViewModel {
    fun signIn(
        username: String,
        password: String,
    ): Call<Any>

    fun updateUiState(state: SignInUiState)
}
