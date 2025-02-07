package ru.glindaquint.everwell.viewModels.api

import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState
import java.util.Date

interface ISimpleCalendarViewModel {
    fun setDate(date: Date = Date())

    fun updateUiState(state: SimpleCalendarUiState)
}
