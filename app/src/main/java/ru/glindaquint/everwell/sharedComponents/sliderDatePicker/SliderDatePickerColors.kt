package ru.glindaquint.everwell.sharedComponents.sliderDatePicker

import androidx.compose.ui.graphics.Color

data class SliderDatePickerColors(
    val dayBackgroundColor: Color,
    val dayForegroundColor: Color,
    val daySelectedBackgroundColor: Color,
    val monthColor: Color,
    val actionsColor: Color,
    val backgroundColors: List<Color> = listOf(Color.Transparent),
)
