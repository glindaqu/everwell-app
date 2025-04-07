package ru.glindaquint.everwell.viewModels.impl

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.network.dto.users.GetUserResponse
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest
import ru.glindaquint.everwell.services.UserService
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import ru.glindaquint.everwell.uiStates.ProfileUiState
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val userService: UserService,
        private val preferencesManager: PreferencesManager,
    ) : ViewModel() {
        private val user = userService.user
        private val _uiState = MutableStateFlow(ProfileUiState())
        val uiState = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                userService.user.collect { userData ->
                    userData?.let { updateUiState(it) }
                }
            }
        }

        private fun updateUiState(userData: GetUserResponse) {
            _uiState.value =
                _uiState.value.copy(
                    badHabits = userData.badHabits,
                    username = userData.username,
                    height = userData.height?.toString() ?: "",
                    weight = userData.weight?.toString() ?: "",
                    firstname = userData.firstname ?: "",
                    lastname = userData.lastname ?: "",
                    patronymic = userData.patronymic ?: "",
                    diseases = userData.diseases ?: "",
                    sex = userData.sex ?: "",
                    birthDate = userData.birthDate,
                )
        }

        fun updateProfile(
            request: UpdateProfileRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            userService.updateProfile(request, onSuccess, onFailure)
        }

        fun logout(navigationCallback: () -> Unit) {
            with(preferencesManager) {
                saveString(PreferencesKeys.PASSWORD, null)
                saveString(PreferencesKeys.USERNAME, null)
                saveString(PreferencesKeys.NETWORK_TOKEN, null)
            }
            navigationCallback()
        }
    }
