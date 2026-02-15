package ru.glindaquint.everwell.services.notificationsScheduler

enum class Repeat(
    val intervalMillis: Long?,
) {
    REPEAT_EVERY_HOUR(60 * 60 * 1000L),
    REPEAT_EVERY_6_HOURS(6 * 60 * 60 * 1000L),
    REPEAT_EVERY_12_HOURS(12 * 60 * 60 * 1000L),
    REPEAT_EVERY_DAY(24 * 60 * 60 * 1000L),
    REPEAT_ONCE_PER_2_DAYS(2 * 24 * 60 * 60 * 1000L),
    REPEAT_ONCE_PER_3_DAYS(3 * 24 * 60 * 60 * 1000L),
    REPEAT_EVERY_WEEK(7 * 24 * 60 * 60 * 1000L),
    REPEAT_EVERY_MONTH(null),
    REPEAT_NO_REPEAT(null),
    REPEAT_CUSTOM(null),
    ;

    companion object {
        fun fromMillis(millis: Long): Repeat = Repeat.entries.find { it.intervalMillis == millis } ?: REPEAT_CUSTOM
    }
}
