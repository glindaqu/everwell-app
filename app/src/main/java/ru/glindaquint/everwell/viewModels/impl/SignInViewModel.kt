package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.viewModels.api.ISignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
    @Inject
    constructor() :
    ViewModel(),
        ISignInViewModel {
        override fun signIn(
            username: String,
            password: String,
        ): Call<Any> {
            TODO("Not yet implemented")
        }

        override fun updateUiState(state: SignInUiState) {
            TODO("Not yet implemented")
        }
    }
