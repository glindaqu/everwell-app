package ru.glindaquint.everwell.sharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
fun LineSpacer(
    color: Color,
    firstPadding: Int = 10,
    secondPadding: Int = 3,
) {
    Spacer(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(firstPadding.dp),
    )
    Spacer(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color),
    )
    Spacer(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(secondPadding.dp),
    )
}
