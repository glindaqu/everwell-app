package ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureMeasure() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        modifier =
            Modifier
                .padding(top = 11.dp)
                .padding(horizontal = 16.dp),
    ) {
        BloodPressureMeasureField(title = "САД", value = "119", subtitle = "мм. рт. ст.")
        BloodPressureMeasureField(title = "ДАД", value = "79", subtitle = "мм. рт. ст.")
        BloodPressureMeasureField(title = "Пульс", value = "70", subtitle = "ударов/мин")
    }
}
