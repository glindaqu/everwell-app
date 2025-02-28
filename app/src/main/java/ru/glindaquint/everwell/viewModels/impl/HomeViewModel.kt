package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import ru.glindaquint.everwell.services.TasksService
import ru.glindaquint.everwell.services.UserService
import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState
import ru.glindaquint.everwell.viewModels.api.IHomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        tasksService: Lazy<TasksService>,
        userService: Lazy<UserService>,
    ) : ViewModel(),
        IHomeViewModel {
        val uiState: StateFlow<HomeUiState> =
            combine(
                tasksService.get().userTasks,
                userService.get().user,
            ) { tasks, user ->
                HomeUiState(tasks = tasks, username = user?.username)
            }.stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState())

        init {
            userService.get().refreshUser()
        }
    }
