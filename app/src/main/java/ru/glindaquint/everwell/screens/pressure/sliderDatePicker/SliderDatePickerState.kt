package ru.glindaquint.everwell.screens.pressure.sliderDatePicker

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar
import java.util.Date

class SliderDatePickerState(
    val month: MutableIntState = mutableIntStateOf(0),
    val day: MutableIntState = mutableIntStateOf(0),
    val year: MutableIntState = mutableIntStateOf(0),
    val date: MutableState<Date> = mutableStateOf(Date()),
) {
    private val calendar = Calendar.getInstance()

    init {
        updateStates()
    }

    fun setDay(day: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        updateStates()
    }

    fun incrementMonth() {
        calendar.set(Calendar.MONTH, month.intValue + 1)
        updateStates()
    }

    fun decrementMonth() {
        calendar.set(Calendar.MONTH, month.intValue - 1)
        updateStates()
    }

    private fun updateStates() {
        month.intValue = calendar.get(Calendar.MONTH)
        year.intValue = calendar.get(Calendar.YEAR)
        day.intValue = calendar.get(Calendar.DAY_OF_MONTH)
        date.value = calendar.time
    }
}
