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
                lineHeight = 20.sp,
                fontFamily = Roboto,
            ),
        labelSmall =
            TextStyle(
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
                fontSize = 14.sp,
                fontFamily = Roboto,
            ),
    )
