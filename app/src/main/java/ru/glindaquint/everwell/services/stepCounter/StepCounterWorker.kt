package ru.glindaquint.everwell.services.stepCounter

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class StepCounterWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        StepCounterRepository.checkDateReset(applicationContext)
        return Result.success()
    }

    companion object {
        fun scheduleDailyReset(context: Context) {
            val dailyRequest =
                PeriodicWorkRequestBuilder<StepCounterWorker>(
                    24,
                    TimeUnit.HOURS,
                    1,
                    TimeUnit.HOURS,
                ).build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "daily_step_reset",
                ExistingPeriodicWorkPolicy.UPDATE,
                dailyRequest,
            )
        }
    }
}
