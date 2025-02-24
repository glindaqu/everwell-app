package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.authorization.signUp.SignUpRequest
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.userService.UserService
import ru.glindaquint.everwell.uiStates.AuthorizationUiState
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
    @Inject
    constructor(
        private val preferenceManager: PreferenceManagerImpl,
        private val userService: Lazy<UserService>,
        val uiState: MutableStateFlow<AuthorizationUiState>,
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
                        AuthorizationUiState(
                            loading = false,
                            error = null,
                            successful = true,
                        ),
                    )
                },
                onFailure = { t ->
                    AuthorizationUiState(
                        loading = false,
                        error = t.message,
                        successful = false,
                    )
                },
            )
        }

        private fun updateUiState(state: AuthorizationUiState) {
            uiState.value = state
        }
    }
