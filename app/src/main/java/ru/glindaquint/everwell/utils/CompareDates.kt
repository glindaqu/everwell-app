package ru.glindaquint.everwell.utils

import java.util.Calendar
import java.util.Date

fun isSameDay(
    date1: Date,
    date2: Date,
): Boolean {
    val cal1 = Calendar.getInstance()
    val cal2 = Calendar.getInstance()

    cal1.setTime(date1)
    cal2.setTime(date2)

    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}
