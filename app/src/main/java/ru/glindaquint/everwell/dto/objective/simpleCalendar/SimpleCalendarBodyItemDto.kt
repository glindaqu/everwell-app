package ru.glindaquint.everwell.dto.objective.simpleCalendar

data class SimpleCalendarBodyItemDto(
    val day: Int,
    val relatedWithCurrentMonth: Boolean,
    val selected: Boolean,
)
