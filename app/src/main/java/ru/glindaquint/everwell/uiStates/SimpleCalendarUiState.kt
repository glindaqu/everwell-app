package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.types.simpleCalendar.SimpleCalendarBodyItemDto
import java.util.Calendar
import java.util.Date

data class SimpleCalendarUiState(
    val currentMonthDay: Int = 0,
    val currentMonth: Int = 0,
    val currentYear: Int = 0,
    val weekDayTitles: List<String> = listOf("вс", "пн", "вт", "ср", "чт", "пт", "сб"),
    val days: List<SimpleCalendarBodyItemDto> = listOf(),
) {
    fun getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, currentYear)
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonthDay)
        return calendar.time
    }
}
