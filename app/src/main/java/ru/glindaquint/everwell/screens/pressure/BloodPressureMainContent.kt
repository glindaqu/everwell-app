package ru.glindaquint.everwell.screens.pressure

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.network.dto.bloodPressure.BloodPressureDto
import ru.glindaquint.everwell.screens.pressure.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.screens.pressure.sliderDatePicker.rememberSliderDatePickerState
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground
import ru.glindaquint.everwell.ui.theme.BloodPressureSecondary
import ru.glindaquint.everwell.viewModels.impl.BloodPressureViewModel
import java.text.SimpleDateFormat

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

@SuppressLint("SimpleDateFormat")
@Suppress("ktlint:standard:function-naming")
@Composable
private fun BloodPressureRecord(bloodPressureDto: BloodPressureDto) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = BloodPressureSecondary, shape = RoundedCornerShape(12.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = SimpleDateFormat("dd.MM.yy").format(bloodPressureDto.measurementDateTime),
                fontSize = 14.sp,
                color = BloodPressureAccent,
            )
            Text(
                text = SimpleDateFormat("HH:mm").format(bloodPressureDto.measurementDateTime),
                fontSize = 14.sp,
                color = BloodPressureAccent,
            )
        }
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BloodPressureAccent),
        )
        Column(
            modifier =
                Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 12.dp)
                    .padding(start = 24.dp),
        ) {
            Text(text = "Systolic pressure: ${bloodPressureDto.systolicPressure}")
            Text(text = "Diastolic pressure: ${bloodPressureDto.diastolicPressure}")
            Text(text = "Heart rate: ${bloodPressureDto.heartRate}")
        }
    }
}
