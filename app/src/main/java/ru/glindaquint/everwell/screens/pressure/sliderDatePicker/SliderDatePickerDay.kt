package ru.glindaquint.everwell.screens.pressure.sliderDatePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent

@Suppress("ktlint:standard:function-naming")
@Composable
fun SliderDatePickerDay() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(start = 16.dp, bottom = 35.dp),
    ) {
        items((1..30).toList()) {
            Box(
                modifier =
                    Modifier
                        .size(42.dp)
                        .background(
                            color = BloodPressureAccent,
                            shape = CircleShape,
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = it.toString(), fontSize = 16.sp, color = Color.White)
            }
        }
    }
}
