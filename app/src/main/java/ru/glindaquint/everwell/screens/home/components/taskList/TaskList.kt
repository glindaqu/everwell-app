package ru.glindaquint.everwell.screens.home.components.taskList

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ru.glindaquint.everwell.network.dto.tasks.TaskDto
import ru.glindaquint.everwell.ui.theme.MainPrimary

@SuppressLint("StateFlowValueCalledInComposition")
@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskList(tasks: List<TaskDto>) {
    TaskListContainer {
        tasks.forEach {
            TaskListItem(task = it)
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "All tasks", color = MainPrimary)
        }
    }
}
