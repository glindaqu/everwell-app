package ru.glindaquint.everwell.uiStates

import java.util.Calendar
import java.util.Date

data class SimpleCalendarUiState(
    val currentMonthDay: Int = 0,
    val currentMonth: Int = 0,
    val currentYear: Int = 0,
    val weekDayTitles: List<String> = listOf("вс", "пн", "вт", "ср", "чт", "пт", "сб"),
    val currentMonthDaysCount: Int = 0,
    val previousMonthDaysCount: Int = 0,
    val firstDayOffset: Int = 0,
    val lastDayOffset: Int = 0,
) {
    fun getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, currentYear)
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonthDay)
        return calendar.time
    }

    fun addDate(
        year: Int = 0,
        month: Int = 0,
        day: Int = 0,
    ): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, currentYear + year)
        calendar.set(Calendar.MONTH, currentMonth + month)
        calendar.set(Calendar.DAY_OF_MONTH, currentMonthDay + day)
        return calendar.time
    }
}
