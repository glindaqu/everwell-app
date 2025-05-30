package ru.glindaquint.everwell.models.simpleCalendar

import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlin.properties.Delegates

class SimpleCalendarModel
    @Inject
    constructor() {
        private val calendar = Calendar.getInstance()

        var currentMonthDay by Delegates.notNull<Int>()
            private set

        var currentMonth by Delegates.notNull<Int>()
            private set

        var currentYear by Delegates.notNull<Int>()
            private set

        var currentMonthDaysCount by Delegates.notNull<Int>()
            private set

        var previousMonthDaysCount by Delegates.notNull<Int>()
            private set

        var firstDayOffset by Delegates.notNull<Int>()
            private set

        var lastDayOffset by Delegates.notNull<Int>()
            private set

        var currentDate by Delegates.notNull<Long>()
            private set

        val date = Date()

        fun year(): Int = calendar.get(Calendar.YEAR)

        fun month(): Int = calendar.get(Calendar.MONTH)

        fun day(): Int = calendar.get(Calendar.DAY_OF_MONTH)

        fun setDate(date: Date = Date()) {
            calendar.time = date

            currentMonthDay = calendar.get(Calendar.DAY_OF_MONTH)
            currentMonth = calendar.get(Calendar.MONTH)
            currentYear = calendar.get(Calendar.YEAR)
            currentMonthDaysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            val currentMonthIndex = calendar.get(Calendar.MONTH)
            calendar.set(Calendar.MONTH, currentMonthIndex - 1)

            previousMonthDaysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            calendar.set(Calendar.MONTH, currentMonthIndex)
            calendar.set(Calendar.DAY_OF_MONTH, 1)

            firstDayOffset = calendar.get(Calendar.DAY_OF_WEEK) - 2

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))

            lastDayOffset = 7 - calendar.get(Calendar.DAY_OF_WEEK)

            calendar.set(Calendar.DAY_OF_MONTH, currentMonthDay)

            currentDate = calendar.time.time
        }

        init {
            setDate()
        }

        companion object {
            fun createDate(
                year: Int? = null,
                month: Int? = null,
                day: Int? = null,
            ): Date {
                val calendar = Calendar.getInstance()
                if (year != null) calendar.set(Calendar.YEAR, year)
                if (month != null) calendar.set(Calendar.MONTH, month)
                if (day != null) calendar.set(Calendar.DAY_OF_MONTH, day)

                return calendar.time
            }

            fun addToDate(
                date: Date,
                year: Int = 0,
                month: Int = 0,
                day: Int = 0,
            ): Date {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year)
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month)
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + day)
                return calendar.time
            }
        }
    }
