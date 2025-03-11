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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.screens.pressure.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.screens.pressure.sliderDatePicker.rememberSliderDatePickerState
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground
import ru.glindaquint.everwell.viewModels.impl.BloodPressureViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureMainContent() {
    val sliderDatePickerState = rememberSliderDatePickerState()
    val viewModel = hiltViewModel<BloodPressureViewModel>()
    val uiState = viewModel.uiState.collectAsState()

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
            onDateSelected = { /*TODO*/ },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            modifier = Modifier.navigationBarsPadding(),
        ) {
            uiState.value.bloodPressures.forEach { bloodPressure ->
                BloodPressureRecord(bloodPressureDto = bloodPressure)
            }
            ActionTile()
            ActionTile()
        }
    }
}
