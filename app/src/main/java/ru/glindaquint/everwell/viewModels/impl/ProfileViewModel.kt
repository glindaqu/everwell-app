package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.glindaquint.everwell.network.dto.users.UpdateProfileRequest
import ru.glindaquint.everwell.services.UserService
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val userService: UserService,
    ) : ViewModel() {
        val user = userService.user

        fun updateProfile(request: UpdateProfileRequest) {
            userService.updateProfile(request)
        }
    }
