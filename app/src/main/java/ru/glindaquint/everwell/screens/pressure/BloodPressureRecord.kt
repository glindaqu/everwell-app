package ru.glindaquint.everwell.screens.pressure

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.network.dto.bloodPressure.BloodPressureDto
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureSecondary
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureRecord(bloodPressureDto: BloodPressureDto) {
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
