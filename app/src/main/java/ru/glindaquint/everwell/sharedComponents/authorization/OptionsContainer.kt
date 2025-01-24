package ru.glindaquint.everwell.sharedComponents.authorization

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun OptionsContainer(content: @Composable () -> Unit) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 41.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
    }
}
