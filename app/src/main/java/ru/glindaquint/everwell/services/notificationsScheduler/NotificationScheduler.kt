package ru.glindaquint.everwell.services.notificationsScheduler

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface NotificationScheduler {
    suspend fun scheduleNotification(
        notificationId: Int,
        notificationData: NotificationData,
        triggerTime: Instant,
        repeatStrategy: Repeat = Repeat.REPEAT_NO_REPEAT,
    )

    fun cancelNotification(notificationId: Int)

    suspend fun updateNotification(
        notificationId: Int,
        notificationData: NotificationData,
        newTriggerTime: Instant,
        newRepeatStrategy: Repeat,
    )

    fun getScheduledNotifications(): Flow<List<Pair<Int, NotificationData>>>
}
