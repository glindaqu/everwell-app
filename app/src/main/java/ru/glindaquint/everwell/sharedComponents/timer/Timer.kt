package ru.glindaquint.everwell.sharedComponents.timer

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("CoroutineCreationDuringComposition", "DefaultLocale")
@Suppress("ktlint:standard:function-naming")
@Composable
fun Timer(state: TimerState) {
    Text(
        text =
            String.format(
                "%d:%02d",
                state.timeRemaining.intValue / 60,
                state.timeRemaining.intValue % 60,
            ),
    )
}
