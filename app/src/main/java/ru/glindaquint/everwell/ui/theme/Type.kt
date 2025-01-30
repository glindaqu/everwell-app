package ru.glindaquint.everwell.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.R

val Roboto =
    FontFamily(
        Font(R.font.roboto_variable),
    )

// Set of Material typography styles to start with
val Typography =
    Typography(
        headlineLarge =
            TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Roboto,
            ),
        bodySmall =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = Roboto,
            ),
        bodyMedium =
            TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                fontFamily = Roboto,
            ),
        labelMedium =
            TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                fontFamily = Roboto,
            ),
    )
