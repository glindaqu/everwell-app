package ru.glindaquint.everwell.viewModels.impl

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.glindaquint.everwell.network.dto.authorization.RestoreRequest
import ru.glindaquint.everwell.services.UserService
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import ru.glindaquint.everwell.sharedComponents.timer.TimerState
import ru.glindaquint.everwell.uiStates.RestoreUiState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RestoreViewModel
    @Inject
    constructor(
        private val userService: UserService,
        private val preferencesManager: PreferencesManager,
        val timerState: TimerState,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(RestoreUiState())
        val uiState = _uiState.asStateFlow()

        @SuppressLint("DefaultLocale")
        fun sendVerificationCode(email: String) {
            val code = String.format("%04d", Random.nextInt(0, 9999))
            userService.sendVerificationCode(email = email, code = code)
            updateUiState(_uiState.value.copy(code = code))
        }

        private fun updateUiState(state: RestoreUiState) {
            _uiState.value = state
        }

        fun resetPassword(
            request: RestoreRequest,
            callback: () -> Unit,
        ) {
            userService.restorePassword(
                request = request,
                onSuccess = {
                    preferencesManager.saveString(PreferencesKeys.PASSWORD, null)
                    callback()
                },
            )
        }
    }
