package ru.glindaquint.everwell.screens.home.components.taskList

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.glindaquint.everwell.network.dto.tasks.TaskDto
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat", "UnrememberedMutableState")
@Suppress("ktlint:standard:function-naming")
@Composable
internal fun TaskListItem(task: TaskDto) {
    val isCompleted = remember { mutableStateOf(task.isCompleted) }

    derivedStateOf {
        // TODO: change status of task completing in viewmodel
    }

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MainPrimary)
                .padding(start = 12.dp)
                .background(MainSecondary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier =
                Modifier
                    .padding(start = 10.dp)
                    .padding(vertical = 15.dp),
        ) {
            Text(text = task.title, fontSize = 14.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = SimpleDateFormat("hh:MM").format(task.deadlineDate),
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    color = MainPrimary,
                )
                if (task.isNotificationEnabled) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "is notification set",
                        modifier = Modifier.size(13.dp),
                        tint = MainPrimary,
                    )
                }
                if (task.repeat != "NO_REPEAT") {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "is notification repeats",
                        modifier = Modifier.size(13.dp),
                        tint = MainPrimary,
                    )
                }
            }
        }
        Checkbox(
            checked = isCompleted.value,
            onCheckedChange = { isCompleted.value = !isCompleted.value },
            colors =
                CheckboxDefaults.colors(
                    checkedColor = MainPrimary,
                    uncheckedColor = MainPrimary,
                ),
        )
    }
}
