package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureAlternatePrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun ActionTile() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = BloodPressureAlternatePrimary, shape = RoundedCornerShape(12.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Measurement history",
                    modifier = Modifier.size(20.dp),
                    tint = BloodPressureAccent,
                )
                Text(text = "History", fontSize = 14.sp, color = BloodPressureAccent)
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(30.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Open measurement history",
                    tint = BloodPressureAccent,
                )
            }
        }
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BloodPressureAccent),
        )
        Text(
            text = "Количество измерений давления за текущий месяц: 38",
            modifier =
                Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 12.dp)
                    .padding(start = 24.dp),
            color = Color.White,
        )
    }
}
