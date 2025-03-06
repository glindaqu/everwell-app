package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.screens.pressure.bloodPressureScale.BloodPressureScale
import ru.glindaquint.everwell.screens.pressure.sliderDatePicker.SliderDatePicker
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureMainContent() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
                .background(
                    color = BloodPressureOnBackground,
                    shape = RoundedCornerShape(20.dp),
                ).padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        SliderDatePicker()
        BloodPressureScale()
        BloodPressureCharacteristic()
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            ActionTile()
            ActionTile()
        }
    }
}
