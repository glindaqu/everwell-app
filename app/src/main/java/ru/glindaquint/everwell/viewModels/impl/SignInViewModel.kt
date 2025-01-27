package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.RetrofitFactory
import ru.glindaquint.everwell.network.dto.authorization.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.SignInResponse
import ru.glindaquint.everwell.network.services.UsersService
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.viewModels.api.ISignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
    @Inject
    constructor() :
    ViewModel(),
        ISignInViewModel {
        private val service = RetrofitFactory.build(UsersService::class.java)
        val uiState = MutableStateFlow(SignInUiState())

        override fun signIn(request: SignInRequest) {
            service
                .signIn(request)
                .enqueue(
                    object : Callback<SignInResponse> {
                        override fun onResponse(
                            call: Call<SignInResponse>,
                            response: Response<SignInResponse>,
                        ) {
                            if (response.body() == null) {
                                updateUiState(
                                    uiState.value.copy(
                                        loading = false,
                                        error = "Something went wrong",
                                    ),
                                )
                            } else {
                                updateUiState(
                                    uiState.value.copy(
                                        loading = false,
                                        data = response.body()!!,
                                    ),
                                )
                            }
                        }

                        override fun onFailure(
                            call: Call<SignInResponse>,
                            t: Throwable,
                        ) {
                            updateUiState(
                                uiState.value.copy(
                                    loading = false,
                                    error = t.message ?: "Unknown error",
                                ),
                            )
                        }
                    },
                )
        }

        override fun updateUiState(state: SignInUiState) {
            uiState.value = state
        }
    }
