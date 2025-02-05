package ru.glindaquint.everwell.screens.home.components.tranparencyDarkBG

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Suppress("ktlint:standard:function-naming")
@Composable
fun TransparencyDarkBackground(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(color)
                .zIndex(1f),
    )
}
