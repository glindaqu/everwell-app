package ru.glindaquint.everwell.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.dto.colors.MainTopBarColors

@Suppress("ktlint:standard:function-naming")
@Composable
fun EverwellScaffold(
    topBarTitle: String,
    topBarColors: MainTopBarColors,
    containerColor: Color,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    contentSpacing: Arrangement.Vertical = Arrangement.Top,
    contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    topBar: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
        topBar = { topBar() },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(containerColor)
                    .padding(bottom = 1.dp)
                    .padding(contentPadding)
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .padding(top = 10.dp),
            verticalArrangement = contentSpacing,
            horizontalAlignment = contentAlignment,
        ) {
            content()
        }
    }
}
