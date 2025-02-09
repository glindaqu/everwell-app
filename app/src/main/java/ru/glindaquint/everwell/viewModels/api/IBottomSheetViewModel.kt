package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.uiStates.BottomSheetUiState

interface IBottomSheetViewModel {
    fun updateUiState(state: BottomSheetUiState)
}
