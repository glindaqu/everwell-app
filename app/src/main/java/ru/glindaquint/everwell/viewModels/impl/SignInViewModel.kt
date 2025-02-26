package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.authorization.signIn.SignInRequest
import ru.glindaquint.everwell.services.preferencesManager.PreferenceManagerImpl
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.userService.UserService
import ru.glindaquint.everwell.uiStates.SignInUiState
import ru.glindaquint.everwell.utils.jwt.JwtUtils
import ru.glindaquint.everwell.viewModels.api.ISignInViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignInViewModel
    @Inject
    constructor(
        private val preferenceManager: PreferenceManagerImpl,
        private val userService: Lazy<UserService>,
        val uiState: MutableStateFlow<SignInUiState>,
    ) : ViewModel(),
        ISignInViewModel {
        private val savedUsername = preferenceManager.getString("username")
        private val savedPassword = preferenceManager.getString("password")

        init {
            if (savedPassword != null && savedUsername != null) {
                signIn(SignInRequest(username = savedUsername, password = savedPassword))
            }
        }

        override fun signIn(request: SignInRequest) {
            updateUiState(uiState.value.copy(loading = true))
            val savedToken = preferenceManager.getString(PreferencesKeys.NETWORK_TOKEN)
            val jwt = JwtUtils.decode(savedToken)

            if (jwt != null && Date(jwt.iat).after(Date())) {
                updateUiState(
                    SignInUiState(
                        loading = false,
                        error = null,
                        successful = true,
                    ),
                )
            } else {
                userService.get().signIn(
                    request = request,
                    onSuccess = { token ->
                        preferenceManager.saveString("token", token)
                        if (savedPassword != request.password) {
                            preferenceManager.saveString("password", request.password)
                        }
                        if (savedUsername != request.username) {
                            preferenceManager.saveString("username", request.username)
                        }
                        updateUiState(
                            SignInUiState(
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
        }

        override fun updateUiState(state: SignInUiState) {
            uiState.value = state
        }
    }
