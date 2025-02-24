package ru.glindaquint.everwell.screens.home.components.taskList

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import ru.glindaquint.everwell.network.dto.tasks.TaskDto

@SuppressLint("StateFlowValueCalledInComposition")
@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskList(tasks: List<TaskDto>) {
    TaskListContainer {
        tasks.forEach {
            TaskListItem(task = it)
        }
    }
}
