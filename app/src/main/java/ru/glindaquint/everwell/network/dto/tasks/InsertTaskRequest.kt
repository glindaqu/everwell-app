package ru.glindaquint.everwell.network.dto.tasks

import ru.glindaquint.everwell.services.notificationsScheduler.Repeat
import java.util.Date

data class InsertTaskRequest(
    val title: String? = null,
    val description: String? = null,
    val repeat: Repeat? = null,
    val isNotificationEnabled: Boolean? = null,
    val deadlineDate: Date? = null,
    val category: String? = null,
)
