package ru.glindaquint.everwell.viewModels.api

import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState
import java.util.Date

interface ISimpleCalendarViewModel {
    val simpleCalendarUiState: MutableStateFlow<SimpleCalendarUiState>

    fun setDate(date: Date)

    fun updateUiState(state: SimpleCalendarUiState)
}
