package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.authorization.SignInRequest
import ru.glindaquint.everwell.network.dto.authorization.SignInResponse
import ru.glindaquint.everwell.network.services.AuthorizationService
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.viewModels.api.ISignInViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
    @Inject
    constructor(
        private val preferenceManager: PreferenceManagerImpl,
        private val authorizationService: AuthorizationService,
        val uiState: MutableStateFlow<SignInUiState>,
    ) : ViewModel(),
        ISignInViewModel {
        override fun signIn(request: SignInRequest) {
            updateUiState(uiState.value.copy(loading = true))
            authorizationService
                .signIn(request)
                .enqueue(
                    object : Callback<SignInResponse> {
                        override fun onResponse(
                            call: Call<SignInResponse>,
                            response: Response<SignInResponse>,
                        ) {
                            if (response.body() == null) {
                                updateUiState(
                                    SignInUiState(
                                        loading = false,
                                        error = "Something went wrong",
                                        successful = false,
                                    ),
                                )
                            } else {
                                updateUiState(
                                    SignInUiState(
                                        loading = false,
                                        error = null,
                                        successful = true,
                                    ),
                                )
                                preferenceManager.saveString("token", response.body()!!.token)
                            }
                        }

                        override fun onFailure(
                            call: Call<SignInResponse>,
                            t: Throwable,
                        ) {
                            updateUiState(
                                SignInUiState(
                                    loading = false,
                                    error = t.message ?: "Unknown error",
                                    successful = false,
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
