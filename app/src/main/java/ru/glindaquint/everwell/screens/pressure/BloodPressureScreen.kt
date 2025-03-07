package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure.BloodPressureMeasure
import ru.glindaquint.everwell.screens.pressure.bloodPressureScale.BloodPressureScale
import ru.glindaquint.everwell.ui.extensions.UpdateSystemBarsColor
import ru.glindaquint.everwell.ui.theme.BloodPressureBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun BloodPressureScreen(drawerState: DrawerState) {
    UpdateSystemBarsColor(
        statusBarColor = Color.Transparent,
        navBarColor = Color.Transparent,
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = BloodPressureBackground,
        topBar = { BloodPressureTopBar(drawerState = drawerState) },
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
            BloodPressureMeasure()
            BloodPressureScale()
            BloodPressureCharacteristic()
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
