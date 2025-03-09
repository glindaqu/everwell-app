package ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.ui.theme.BloodPressureAccent
import ru.glindaquint.everwell.ui.theme.BloodPressureAlternatePrimary
import ru.glindaquint.everwell.ui.theme.BloodPressureOnBackground
import ru.glindaquint.everwell.ui.theme.BloodPressureSecondary
import ru.glindaquint.everwell.utils.pxToDp

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.BloodPressureMeasureField(
    title: String,
    subtitle: String,
    value: TextFieldValue,
    showMeasurementUnits: Boolean,
    onValueChanged: (TextFieldValue) -> Unit,
) {
    // 40 / viewSize = x / changedViewSize
    // x = 40 * changedViewSize / viewSize
    val viewWidth = remember { mutableStateOf(1.dp) }
    val currentViewWidth = remember { mutableStateOf(1.dp) }

    Column(
        modifier =
            Modifier
                .weight(0.3f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        if (viewWidth.value == 1.dp) {
                            viewWidth.value = it.size.width.pxToDp()
                        }
                        currentViewWidth.value = it.size.width.pxToDp()
                    }.height(currentViewWidth.value)
                    .background(
                        color = BloodPressureAlternatePrimary,
                        shape = RoundedCornerShape(12.dp),
                    ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = value,
                singleLine = true,
                onValueChange = {
                    if (it.text.length <= 3) {
                        onValueChanged(it)
                    }
                },
                textStyle =
                    TextStyle(
                        fontSize = (40 * currentViewWidth.value.value.toInt() / viewWidth.value.value.toInt()).sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    ),
                label = {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                },
                colors =
                    TextFieldDefaults.colors(
                        focusedContainerColor = BloodPressureAlternatePrimary,
                        unfocusedContainerColor = BloodPressureAlternatePrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = BloodPressureSecondary,
                        cursorColor = BloodPressureOnBackground,
                        disabledContainerColor = BloodPressureAlternatePrimary,
                        disabledIndicatorColor = Color.Transparent,
                        disabledLabelColor = Color.White,
                    ),
                shape = RoundedCornerShape(12.dp),
            )
            if (showMeasurementUnits) {
                Text(text = subtitle, fontSize = 12.sp, color = BloodPressureAccent)
            }
        }
    }
}
