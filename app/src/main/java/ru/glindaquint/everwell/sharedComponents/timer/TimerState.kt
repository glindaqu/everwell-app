package ru.glindaquint.everwell.sharedComponents.timer

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.delay

class TimerState(
    initialTime: Int,
) {
    var timeRemaining = mutableIntStateOf(initialTime)
        private set
    var isRunning = mutableStateOf(false)
        private set

    suspend fun start() {
        isRunning.value = true
        while (timeRemaining.intValue > 0 && isRunning.value) {
            delay(1000)
            timeRemaining.intValue -= 1
        }
        isRunning.value = false
    }

    fun stop() {
        isRunning.value = false
    }

    fun reset(newTime: Int) {
        timeRemaining.intValue = newTime
    }
}
