package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.glindaquint.everwell.services.UserService
import javax.inject.Inject

@HiltViewModel
class HomeNavigationViewModel
    @Inject
    constructor(
        private val userService: Lazy<UserService>,
    ) : ViewModel() {
        val user = userService.get().user
    }
