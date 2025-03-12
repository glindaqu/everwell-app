package ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.network.dto.bloodPressure.AddBloodPressureRequest
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary
import ru.glindaquint.everwell.ui.theme.BloodPressureSecondary
import ru.glindaquint.everwell.utils.pxToDp
import ru.glindaquint.everwell.viewModels.impl.BloodPressureViewModel

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureMeasure(
    systolicPressureState: MutableState<TextFieldValue>,
    diastolicPressureState: MutableState<TextFieldValue>,
    heartRateState: MutableState<TextFieldValue>,
) {
    val showAction = remember { mutableStateOf(false) }
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }

    val viewModel = hiltViewModel<BloodPressureViewModel>()

    LaunchedEffect(
        systolicPressureState.value,
        diastolicPressureState.value,
        heartRateState.value,
    ) {
        if (systolicPressureState.value.text.isNotEmpty() &&
            diastolicPressureState.value.text.isNotEmpty() &&
            heartRateState.value.text.isNotEmpty()
        ) {
            delay(2000)
            showAction.value = true
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(17.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .padding(top = 11.dp)
                .padding(horizontal = 16.dp)
                .onGloballyPositioned {
                    viewSize.value = it.size
                },
    ) {
        BloodPressureMeasureField(
            title = "САД",
            value = systolicPressureState.value,
            subtitle = "мм. рт. ст.",
            showMeasurementUnits = !showAction.value,
            onValueChanged = {
                systolicPressureState.value = it
            },
        )
        BloodPressureMeasureField(
            title = "ДАД",
            value = diastolicPressureState.value,
            subtitle = "мм. рт. ст.",
            showMeasurementUnits = !showAction.value,
            onValueChanged = {
                diastolicPressureState.value = it
            },
        )
        BloodPressureMeasureField(
            title = "Пульс",
            value = heartRateState.value,
            subtitle = "ударов/мин",
            showMeasurementUnits = !showAction.value,
            onValueChanged = {
                heartRateState.value = it
            },
        )

        if (showAction.value) {
            Box(
                modifier = Modifier.height(viewSize.value.height.pxToDp()),
                contentAlignment = Alignment.Center,
            ) {
                ActionButton(
                    contentDescription = "Save",
                    icon = Icons.Filled.Done,
                    onClick = {
                        viewModel.addBloodPressure(
                            AddBloodPressureRequest(
                                systolicPressure = systolicPressureState.value.text.toInt(),
                                diastolicPressure = diastolicPressureState.value.text.toInt(),
                                heartRate = heartRateState.value.text.toInt(),
                            ),
                        )
                        showAction.value = false
                    },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun ActionButton(
    contentDescription: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    val viewSize = remember { mutableStateOf(IntSize(0, 0)) }

    Button(
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = BloodPressurePrimary,
            ),
        modifier =
            Modifier
                .onGloballyPositioned {
                    viewSize.value = it.size
                }.height(viewSize.value.width.pxToDp())
                .wrapContentWidth(),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = BloodPressureSecondary,
        )
    }
}
