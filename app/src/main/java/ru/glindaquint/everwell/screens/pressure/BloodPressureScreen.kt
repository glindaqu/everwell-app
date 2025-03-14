package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure.BloodPressureMeasure
import ru.glindaquint.everwell.screens.pressure.bloodPressureScale.BloodPressureScale
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.extensions.UpdateSystemBarsColor
import ru.glindaquint.everwell.ui.theme.BloodPressureBackground
import ru.glindaquint.everwell.ui.theme.BloodPressurePrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScreen(drawerState: DrawerState) {
    UpdateSystemBarsColor(
        statusBarColor = Color.Transparent,
        navBarColor = Color.Transparent,
    )

    val systolicPressureState = remember { mutableStateOf(TextFieldValue()) }
    val diastolicPressureState = remember { mutableStateOf(TextFieldValue()) }
    val heartRateState = remember { mutableStateOf(TextFieldValue()) }

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

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = BloodPressureBackground,
        topBar = {
            MainTopBar(
                drawerState = drawerState,
                title = "Давление",
                icon = Icons.Filled.Menu,
                colors =
                    MainTopBarColors(
                        backgroundColor = BloodPressurePrimary,
                        foregroundColor = Color.White,
                    ),
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(bottom = 1.dp)
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
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
}

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun Test_Pressure() {
    BloodPressureScreen(rememberDrawerState(initialValue = DrawerValue.Closed))
}
