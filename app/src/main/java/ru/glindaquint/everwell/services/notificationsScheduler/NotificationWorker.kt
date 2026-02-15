package ru.glindaquint.everwell.services.notificationsScheduler

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.time.Instant

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val scheduler =
            (applicationContext as? NotificationSchedulerProvider)?.getScheduler()
                ?: return Result.failure()

        val notificationId = inputData.getInt("notificationId", 0)
        val title = inputData.getString("title") ?: return Result.failure()
        val message = inputData.getString("message") ?: return Result.failure()
        val channelId = inputData.getString("channelId") ?: return Result.failure()
        val smallIcon = inputData.getInt("smallIcon", 0)

        val data =
            NotificationData(
                title = title,
                message = message,
                channelId = channelId,
                smallIcon = smallIcon,
            )

        scheduler.showNotification(notificationId, data)
        return Result.success()
    }
}

class MonthlyNotificationRescheduler(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val scheduler =
            (applicationContext as? NotificationSchedulerProvider)?.getScheduler()
                ?: return Result.failure()

        val notificationId = inputData.getInt("notificationId", 0)
        val title = inputData.getString("title") ?: return Result.failure()
        val message = inputData.getString("message") ?: return Result.failure()
        val channelId = inputData.getString("channelId") ?: return Result.failure()
        val smallIcon = inputData.getInt("smallIcon", 0)
        val originalTrigger =
            inputData
                .getLong("nextTrigger", 0L)
                .takeIf { it > 0 }
                ?.let { Instant.ofEpochMilli(it) } ?: return Result.failure()

        val data =
            NotificationData(
                title = title,
                message = message,
                channelId = channelId,
                smallIcon = smallIcon,
            )

        // Планируем на следующий месяц
        scheduler.scheduleNotification(
            notificationId = notificationId,
            notificationData = data,
            triggerTime = originalTrigger,
            repeatStrategy = Repeat.REPEAT_EVERY_MONTH,
        )

        return Result.success()
    }
}

interface NotificationSchedulerProvider {
    fun getScheduler(): AndroidNotificationScheduler
}
