package ru.glindaquint.everwell.utils

import java.util.Calendar
import java.util.Date

fun isSameDay(
    date1: Date,
    date2: Date,
): Boolean {
    val calendar1 = Calendar.getInstance()
    calendar1.time = date1

    val calendar2 = Calendar.getInstance()
    calendar2.time = date2

    return (calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) &&
        (
            calendar1.get(
                Calendar.MONTH,
            ) == calendar2.get(Calendar.MONTH)
        ) &&
        (
            calendar1.get(Calendar.DAY_OF_MONTH) ==
                calendar2.get(
                    Calendar.DAY_OF_MONTH,
                )
        )
}
