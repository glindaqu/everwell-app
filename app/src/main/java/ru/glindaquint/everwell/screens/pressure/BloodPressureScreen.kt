package ru.glindaquint.everwell.screens.pressure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.screens.pressure.bloodPressureMeasure.BloodPressureMeasure
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
        topBar = { BloodPressureTopBar(drawerState = drawerState) },
        containerColor = BloodPressureBackground,
    ) { paddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .navigationBarsPadding()
                    .padding()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp),
        ) {
            BloodPressureMeasure()
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
