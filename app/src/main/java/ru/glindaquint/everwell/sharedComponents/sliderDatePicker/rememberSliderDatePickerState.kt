@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.sharedComponents.sliderDatePicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberSliderDatePickerState(): SliderDatePickerState = remember { SliderDatePickerState() }
