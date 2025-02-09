package ru.glindaquint.everwell.screens.home.components.taskList

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.viewModels.impl.TaskListViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskList() {
    val viewModel = hiltViewModel<TaskListViewModel>()

    TaskListContainer {
        viewModel.taskListUiState.value.tasks.forEach {
            TaskListItem(task = it)
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "All tasks", color = MainPrimary)
        }
    }
}
