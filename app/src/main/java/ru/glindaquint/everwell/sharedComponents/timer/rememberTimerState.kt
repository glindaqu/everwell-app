@file:Suppress("ktlint:standard:filename")

package ru.glindaquint.everwell.sharedComponents.timer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberTimerState(): TimerState =
    remember {
        TimerState()
    }
