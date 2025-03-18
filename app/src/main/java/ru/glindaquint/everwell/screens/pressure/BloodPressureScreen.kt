package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure.BloodPressureMeasure
import ru.glindaquint.everwell.screens.pressure.bloodPressureScale.BloodPressureScale
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.BloodPressureBackground
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScreen(drawerState: DrawerState) {
    val systolicPressureState = remember { mutableStateOf(TextFieldValue()) }
    val diastolicPressureState = remember { mutableStateOf(TextFieldValue()) }
    val heartRateState = remember { mutableStateOf(TextFieldValue()) }

    val coroutineScope = rememberCoroutineScope()

    val diastolicValue =
        remember {
            derivedStateOf {
                when {
                    systolicPressureState.value.text.isEmpty() ||
                        diastolicPressureState.value.text.isEmpty() -> 0

                    else -> diastolicPressureState.value.text.toInt()
                }
            }
        }

    val systolicValue =
        remember {
            derivedStateOf {
                when {
                    systolicPressureState.value.text.isEmpty() ||
                        diastolicPressureState.value.text.isEmpty() -> 0

                    else -> systolicPressureState.value.text.toInt()
                }
            }
        }

    EverwellScaffold(
        containerColor = BloodPressureBackground,
        contentSpacing = Arrangement.spacedBy(20.dp),
        topBar = {
            MainTopBar(
                icon = Icons.Filled.Menu,
                title = "Feed",
                colors =
                    MainTopBarColors(
                        backgroundColor = BloodPressurePrimary,
                        foregroundColor = Color.White,
                        behindContainerColor = BloodPressureBackground,
                    ),
                onIconClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
    ) {
        BloodPressureMeasure(
            systolicPressureState = systolicPressureState,
            diastolicPressureState = diastolicPressureState,
            heartRateState = heartRateState,
        )
        BloodPressureScale(
            diastolic = diastolicValue.value,
            systolic = systolicValue.value,
        )
        BloodPressureCharacteristic(
            diastolic = diastolicValue.value,
            systolic = systolicValue.value,
        )
        BloodPressureMainContent()
    }
}
