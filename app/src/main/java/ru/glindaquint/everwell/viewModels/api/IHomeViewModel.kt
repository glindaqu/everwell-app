package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState

interface IHomeViewModel {
    fun loadTasks()

    fun updateUiState(state: HomeUiState)
}
