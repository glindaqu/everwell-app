package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.notifications.NotificationDto
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel
    @Inject
    constructor() : ViewModel() {
        val categories = MutableStateFlow(listOf("Все", "Непрочитанные", "Еда", "Лекарства"))
        val notifications =
            MutableStateFlow(
                listOf(
                    NotificationDto(
                        category = "Unread",
                        title = "title",
                        description = "desc",
                        receivingDateTime = Date(),
                        repeatType = "EVERYDAY",
                        isRead = false,
                    ),
                    NotificationDto(
                        category = "Unread",
                        title = "title 2",
                        description = "desc 2",
                        receivingDateTime = Date(),
                        repeatType = "EVERYDAY",
                        isRead = false,
                    ),
                ),
            )
    }
