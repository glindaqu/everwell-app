package ru.glindaquint.everwell.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ru.glindaquint.everwell.services.stepCounter.StepCounterRepository
import ru.glindaquint.everwell.services.stepCounter.StepCounterService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent?,
    ) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val prefs =
                context.getSharedPreferences(
                    StepCounterRepository.PREFS_NAME,
                    Context.MODE_PRIVATE,
                )
            val wasRunning = prefs.getBoolean("service_running", false)

            if (wasRunning) {
                StepCounterService.start(context)
            }
        }
    }
}
