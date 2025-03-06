package ru.glindaquint.everwell.screens.pressure.sliderDatePicker

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.glindaquint.everwell.R
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent

@Suppress("ktlint:standard:function-naming")
@Composable
fun SliderDatePickerMonth() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "prev month",
                tint = BloodPressureAccent,
            )
        }
        Text(text = "ЯНВАРЬ 2025", color = Color.White)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "next month",
                tint = BloodPressureAccent,
            )
        }
    }
}
