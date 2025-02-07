package ru.glindaquint.everwell.types.simpleCalendar

data class SimpleCalendarBodyItemDto(
    val day: Int,
    val relatedWithCurrentMonth: Boolean,
    val selected: Boolean,
)
