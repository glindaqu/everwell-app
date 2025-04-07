package ru.glindaquint.everwell.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToLocalDate(date: Date): LocalDate =
    date
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
