package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.SliderDatePickerColors
import ru.glindaquint.everwell.sharedComponents.sliderDatePicker.rememberSliderDatePickerState
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary
import ru.glindaquint.everwell.viewModels.impl.BloodPressureViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureMainContent() {
    val viewModel = hiltViewModel<BloodPressureViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    val sliderDatePickerState = rememberSliderDatePickerState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    color = BloodPressureOnBackground,
                    shape = RoundedCornerShape(20.dp),
                ).padding(bottom = 20.dp)
                .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SliderDatePicker(
            state = sliderDatePickerState,
            colors =
                SliderDatePickerColors(
                    dayBackgroundColor = BloodPressureAccent,
                    dayForegroundColor = Color.White,
                    backgroundColors = listOf(BloodPressurePrimary, Color(0xffFF9E81)),
                    monthColor = Color.White,
                    actionsColor = BloodPressureAccent,
                    daySelectedBackgroundColor = BloodPressureAccent.copy(0.4f),
                ),
            onDateSelected = { viewModel.filterBloodPressures(sliderDatePickerState.date.value) },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.navigationBarsPadding(),
        ) {
            uiState.value.bloodPressures.forEach { bloodPressure ->
                BloodPressureRecord(bloodPressureDto = bloodPressure)
            }
            ActionTile(
                text = "Количество измерений давления за текущий месяц: ${uiState.value.measurementsCount}",
            )
        }
    }
}
