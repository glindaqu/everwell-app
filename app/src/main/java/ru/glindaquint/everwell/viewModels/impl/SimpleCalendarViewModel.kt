package ru.glindaquint.everwell.viewModels.impl

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.uiStates.SimpleCalendarUiState
import ru.glindaquint.everwell.viewModels.api.ISimpleCalendarViewModel
import java.util.Date

class SimpleCalendarViewModel(
    override val simpleCalendarUiState: MutableStateFlow<SimpleCalendarUiState> =
        MutableStateFlow(
            SimpleCalendarUiState(),
        ),
) : ViewModel(),
    ISimpleCalendarViewModel {
    private val calendar = Calendar.getInstance()

    init {
        setDate(Date())
    }

    override fun setDate(date: Date) {
        calendar.time = date
        val currentMonthDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonthDaysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        val previousMonthDaysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOffset = calendar.get(Calendar.DAY_OF_WEEK) - 2
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val lastDayOffset = 7 - calendar.get(Calendar.DAY_OF_WEEK)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonthDay)

        updateUiState(
            SimpleCalendarUiState(
                currentMonthDay = currentMonthDay,
                currentMonth = calendar.get(Calendar.MONTH),
                currentYear = calendar.get(Calendar.YEAR),
                currentMonthDaysCount = currentMonthDaysCount,
                previousMonthDaysCount = previousMonthDaysCount,
                firstDayOffset = firstDayOffset,
                lastDayOffset = lastDayOffset,
            ),
        )
    }

    override fun updateUiState(state: SimpleCalendarUiState) {
        simpleCalendarUiState.value = state
    }
}
