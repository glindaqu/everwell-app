package ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureAlternatePrimary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.BloodPressureMeasureField(
    title: String,
    value: String,
    subtitle: String,
) {
    val componentWidth = remember { mutableIntStateOf(0) }

    Column(
        modifier =
            Modifier
                .weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        componentWidth.intValue = it.size.width
                    }.height(componentWidth.intValue.pxToDp())
                    .background(
                        color = BloodPressureAlternatePrimary,
                        shape = RoundedCornerShape(12.dp),
                    ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = value, fontSize = 40.sp, color = Color.White)
            Text(text = subtitle, fontSize = 12.sp, color = BloodPressureAccent)
        }
    }
}
