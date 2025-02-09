package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.uiStates.TaskListUiState

interface ITaskListViewModel {
    fun updateUiState(state: TaskListUiState)
}
