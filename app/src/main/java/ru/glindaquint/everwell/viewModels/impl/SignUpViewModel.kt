package ru.glindaquint.everwell.viewModels.impl

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.services.UserService
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.uiStates.SignUpUiState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SignUpViewModel
    @Inject
    constructor(
        private val preferenceManager: PreferenceManagerImpl,
        private val userService: Lazy<UserService>,
        val uiState: MutableStateFlow<SignUpUiState>,
    ) : ViewModel() {
        fun signUp(request: SignUpRequest) {
            updateUiState(uiState.value.copy(loading = true))
            userService.get().signUp(
                request = request,
                onSuccess = { token ->
                    preferenceManager.saveString(PreferencesKeys.NETWORK_TOKEN, token)
                    preferenceManager.saveString(PreferencesKeys.PASSWORD, request.password)
                    preferenceManager.saveString(PreferencesKeys.USERNAME, request.username)
                    updateUiState(
                        SignUpUiState(
                            loading = false,
                            error = null,
                            successful = true,
                        ),
                    )
                },
                onFailure = { t ->
                    SignInUiState(
                        loading = false,
                        error = t.message,
                        successful = false,
                    )
                },
            )
        }

        @SuppressLint("DefaultLocale")
        fun sendCode(email: String) {
            val code = String.format("%04d", Random.nextInt(9999))
            userService.get().sendVerificationCode(email = email, code = code)
            updateUiState(uiState.value.copy(code = code))
        }

        private fun updateUiState(state: SignUpUiState) {
            uiState.value = state
        }
    }
