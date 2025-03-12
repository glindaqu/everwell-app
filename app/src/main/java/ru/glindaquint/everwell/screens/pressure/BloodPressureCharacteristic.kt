package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary

@Composable
@Suppress("ktlint:standard:function-naming")
fun BloodPressureCharacteristic(
    systolic: Int,
    diastolic: Int,
) {
    val systolicTitleState = remember { mutableStateOf("") }
    val diastolicTitleState = remember { mutableStateOf("") }
    val widgetTitleState = remember { mutableStateOf("") }

    when {
        systolic > 180 || diastolic > 110 -> {
            systolicTitleState.value = ">180"
            diastolicTitleState.value = ">110"
            widgetTitleState.value = "Тяжелая гипертония"
        }

        systolic in 160..179 || diastolic in 100..109 -> {
            systolicTitleState.value = "160-179"
            diastolicTitleState.value = "100-109"
            widgetTitleState.value = "Гипертония средней тяжести"
        }

        systolic in 140..159 || diastolic in 90..99 -> {
            systolicTitleState.value = "140-159"
            diastolicTitleState.value = "90-99"
            widgetTitleState.value = "Умеренная гипертония (мягкая)"
        }

        systolic in 130..139 || diastolic in 85..89 -> {
            systolicTitleState.value = "130-139"
            diastolicTitleState.value = "85-89"
            widgetTitleState.value = "Высокое нормальное давление"
        }

        systolic in 120..129 || diastolic in 80..84 -> {
            systolicTitleState.value = "120-129"
            diastolicTitleState.value = "80-84"
            widgetTitleState.value = "Нормальное давление"
        }

        systolic in 101..118 || diastolic in 60..79 -> {
            systolicTitleState.value = "101-118"
            diastolicTitleState.value = "60-79"
            widgetTitleState.value = "Оптимальное давление"
        }

        systolic in 1..100 || diastolic in 1..60 -> {
            systolicTitleState.value = "<100"
            diastolicTitleState.value = "<60"
            widgetTitleState.value = "Гипотония"
        }

        else -> {
            systolicTitleState.value = "0_0"
            diastolicTitleState.value = ":3"
            widgetTitleState.value = "Измерьте свое давление"
        }
    }

    Column(
        modifier =
            Modifier
                .background(
                    color = BloodPressurePrimary,
                    shape = RoundedCornerShape(12.dp),
                ).padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = widgetTitleState.value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "About pressure",
                tint = Color.White.copy(0.5f),
                modifier = Modifier.size(20.dp),
            )
        }
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Systolic pressure ")
                    }
                    withStyle(style = SpanStyle(Color.White.copy(0.5f))) {
                        append(systolicTitleState.value)
                    }
                },
        )
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Diastolic pressure ")
                    }
                    withStyle(style = SpanStyle(Color.White.copy(0.5f))) {
                        append(diastolicTitleState.value)
                    }
                },
        )
    }
}
