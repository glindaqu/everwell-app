package ru.glindaquint.everwell.screens.home.components.taskList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.glindaquint.everwell.ui.theme.MainPrimary

@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskList() {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TaskListItem(title = "Save Viktor", deadlineTime = "10:00")
        TaskListItem(title = "Create a hextech", deadlineTime = "11:00")

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "All tasks", color = MainPrimary)
        }
    }
}
