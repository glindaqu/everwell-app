@file:Suppress("ktlint:standard:no-wildcard-imports")

package ru.glindaquint.everwell.services.notificationsScheduler

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AndroidNotificationScheduler
    @Inject
    constructor(
        private val context: Context,
        private val notificationManager: NotificationManagerCompat,
    ) : NotificationScheduler {
        private val workManager = WorkManager.getInstance(context)

        @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
        override suspend fun scheduleNotification(
            notificationId: Int,
            notificationData: NotificationData,
            triggerTime: Instant,
            repeatStrategy: Repeat,
        ) {
            when {
                triggerTime.isBefore(Instant.now()) && repeatStrategy == Repeat.REPEAT_NO_REPEAT -> {
                    showNotification(notificationId, notificationData)
                }

                repeatStrategy == Repeat.REPEAT_NO_REPEAT -> {
                    scheduleOneTimeNotification(notificationId, notificationData, triggerTime)
                }

                repeatStrategy == Repeat.REPEAT_EVERY_MONTH -> {
                    scheduleMonthlyNotification(notificationId, notificationData, triggerTime)
                }

                else -> {
                    schedulePeriodicNotification(
                        notificationId,
                        notificationData,
                        triggerTime,
                        repeatStrategy,
                    )
                }
            }
        }

        override fun cancelNotification(notificationId: Int) {
            workManager.cancelAllWorkByTag(notificationId.toString())
        }

        @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
        override suspend fun updateNotification(
            notificationId: Int,
            notificationData: NotificationData,
            newTriggerTime: Instant,
            newRepeatStrategy: Repeat,
        ) {
            cancelNotification(notificationId)
            scheduleNotification(notificationId, notificationData, newTriggerTime, newRepeatStrategy)
        }

        override fun getScheduledNotifications(): Flow<List<Pair<Int, NotificationData>>> {
            return workManager.getWorkInfosByTagFlow("").map { workInfos ->
                workInfos
                    .filter { workInfo ->
                        workInfo.state == WorkInfo.State.ENQUEUED
                    }.mapNotNull { workInfo ->
                        val notificationId =
                            workInfo.tags.firstOrNull()?.toIntOrNull() ?: return@mapNotNull null
                        val inputData = workInfo.progress

                        val title = inputData.getString("title") ?: return@mapNotNull null
                        val message = inputData.getString("message") ?: return@mapNotNull null
                        val channelId = inputData.getString("channelId") ?: return@mapNotNull null
                        val smallIcon = inputData.getInt("smallIcon", 0)

                        val nextTriggerTime =
                            when (workInfo.state) {
                                WorkInfo.State.ENQUEUED -> calculateNextTriggerTime(workInfo)
                                else -> null
                            }

                        notificationId to
                            NotificationData(
                                title = title,
                                message = message,
                                channelId = channelId,
                                smallIcon = smallIcon,
                                extras =
                                    Bundle().apply {
                                        nextTriggerTime?.let { putLong("nextTrigger", it.toEpochMilli()) }
                                    },
                            )
                    }
            }
        }

        private fun calculateNextTriggerTime(workInfo: WorkInfo): Instant? =
            when {
                // Для периодических задач
                workInfo.runAttemptCount > 0 -> {
                    val interval = workInfo.progress.getLong("repeatInterval", 0L)
                    if (interval > 0) {
                        Instant.now().plusMillis(interval)
                    } else {
                        null
                    }
                }

                true -> {
                    workInfo.nextScheduleTimeMillis.takeIf { it > 0 }?.let {
                        Instant.ofEpochMilli(it)
                    }
                }

                else -> null
            }

        private suspend fun scheduleOneTimeNotification(
            notificationId: Int,
            notificationData: NotificationData,
            triggerTime: Instant,
        ) {
            val delay = ChronoUnit.MILLIS.between(Instant.now(), triggerTime)

            val request =
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInputData(createWorkInput(notificationId, notificationData))
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .addTag(notificationId.toString())
                    .build()

            workManager.enqueue(request)
        }

        private suspend fun schedulePeriodicNotification(
            notificationId: Int,
            notificationData: NotificationData,
            triggerTime: Instant,
            repeatStrategy: Repeat,
        ) {
            val initialDelay = ChronoUnit.MILLIS.between(Instant.now(), triggerTime)
            val repeatInterval = repeatStrategy.intervalMillis ?: return

            val request =
                PeriodicWorkRequestBuilder<NotificationWorker>(
                    repeatInterval = repeatInterval,
                    TimeUnit.MILLISECONDS,
                ).setInputData(createWorkInput(notificationId, notificationData))
                    .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                    .addTag(notificationId.toString())
                    .build()

            workManager.enqueue(request)
        }

        private suspend fun scheduleMonthlyNotification(
            notificationId: Int,
            notificationData: NotificationData,
            triggerTime: Instant,
        ) {
            val now = Instant.now()
            var nextTrigger = triggerTime

            // Находим следующую дату в следующем месяце
            while (nextTrigger.isBefore(now)) {
                nextTrigger = nextTrigger.atZone(ZoneId.systemDefault()).plusMonths(1).toInstant()
            }

            val delay = ChronoUnit.MILLIS.between(now, nextTrigger)

            val request =
                OneTimeWorkRequestBuilder<NotificationWorker>()
                    .setInputData(createWorkInput(notificationId, notificationData, nextTrigger))
                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                    .addTag(notificationId.toString())
                    .build()

            workManager
                .beginWith(request)
                .then(
                    OneTimeWorkRequestBuilder<MonthlyNotificationRescheduler>()
                        .setInputData(createWorkInput(notificationId, notificationData, triggerTime))
                        .build(),
                ).enqueue()
        }

        @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
        internal fun showNotification(
            notificationId: Int,
            data: NotificationData,
        ) {
            val notification =
                NotificationCompat
                    .Builder(context, data.channelId)
                    .setContentTitle(data.title)
                    .setContentText(data.message)
                    .setSmallIcon(data.smallIcon)
                    .setPriority(data.priority)
                    .setAutoCancel(data.autoCancel)
                    .setExtras(data.extras)
                    .build()

            notificationManager.notify(notificationId, notification)
        }

        private fun createWorkInput(
            notificationId: Int,
            data: NotificationData,
            nextTrigger: Instant? = null,
        ): Data =
            workDataOf(
                "notificationId" to notificationId,
                "title" to data.title,
                "message" to data.message,
                "channelId" to data.channelId,
                "smallIcon" to data.smallIcon,
                "nextTrigger" to nextTrigger?.toEpochMilli(),
            )
    }
