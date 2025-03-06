package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary

@Composable
@Suppress("ktlint:standard:function-naming")
fun BloodPressureCharacteristic() {
    Column(
        modifier =
            Modifier
                .background(
                    color = BloodPressurePrimary,
                    shape = RoundedCornerShape(12.dp),
                ).padding(horizontal = 32.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Text(
                text = "Normal pressure",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "About pressure",
                tint = Color.White.copy(0.5f),
                modifier = Modifier.size(20.dp),
            )
        }
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Systolic pressure ")
                    }
                    withStyle(style = SpanStyle(Color.White.copy(0.5f))) {
                        append("90-199")
                    }
                },
        )
        Text(
            text =
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.White)) {
                        append("Systolic pressure ")
                    }
                    withStyle(style = SpanStyle(Color.White.copy(0.5f))) {
                        append("90-199")
                    }
                },
        )
    }
}
