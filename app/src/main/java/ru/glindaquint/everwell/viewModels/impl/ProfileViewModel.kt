package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest
import ru.glindaquint.everwell.services.UserService
import ru.glindaquint.everwell.services.preferencesManager.PreferencesKeys
import ru.glindaquint.everwell.services.preferencesManager.PreferencesManager
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val userService: UserService,
        private val preferencesManager: PreferencesManager,
    ) : ViewModel() {
        val user = userService.user

        fun updateProfile(
            request: UpdateProfileRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            userService.updateProfile(
                request = request,
                onSuccess = onSuccess,
                onFailure = onFailure,
            )
        }

        fun logout(navigationCallback: () -> Unit) {
            preferencesManager.saveString(PreferencesKeys.PASSWORD, null)
            preferencesManager.saveString(PreferencesKeys.USERNAME, null)
            preferencesManager.saveString(PreferencesKeys.NETWORK_TOKEN, null)

            navigationCallback()
        }
    }
