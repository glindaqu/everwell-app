package ru.glindaquint.everwell.screens.pressure.bloodPressureScale

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScaleHandle(height: Dp) {
    Column(
        modifier =
            Modifier.offset(x = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier =
                Modifier
                    .width(5.dp)
                    .height(height)
                    .background(Color.White),
        )
        Text(text = "119/79", fontSize = 12.sp, color = Color.White)
    }
}
