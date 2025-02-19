package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.uiStates.homeUiState.HomeUiState

interface IHomeViewModel {
    fun loadUser()

    fun loadTasks()

    fun updateUiState(state: HomeUiState)
}
