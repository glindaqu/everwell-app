package ru.glindaquint.everwell.screens.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.network.dto.tasks.TaskDto
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.MainAccent
import ru.glindaquint.everwell.ui.theme.MainBackground
import ru.glindaquint.everwell.ui.theme.MainOnBackground
import ru.glindaquint.everwell.ui.theme.MainPrimary
import ru.glindaquint.everwell.ui.theme.MainSecondary
import ru.glindaquint.everwell.viewModels.impl.TaskViewModel
import kotlin.random.Random

@Suppress("ktlint:standard:function-naming")
@Composable
fun TasksScreen(
    navHostController: NavHostController,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<TaskViewModel>()
    val tasks = viewModel.allTasks.collectAsState()

    EverwellScaffold(
        containerColor = MainBackground,
        contentPadding = PaddingValues(10.dp),
        topBar = {
            MainTopBar(
                icon = Icons.Default.Menu,
                title = "Tasks",
                colors =
                    MainTopBarColors(
                        backgroundColor = MainPrimary,
                        foregroundColor = MainSecondary,
                        behindContainerColor = MainBackground,
                    ),
                onIconClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                shape = CircleShape,
                containerColor = MainPrimary,
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add new task",
                )
            }
        },
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(tasks.value) { task ->
                TaskCategoryItem(title = task.category, count = Random.nextInt(0, 4))
            }
        }
        TasksList(tasks = tasks.value)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskCategoryItem(
    title: String,
    count: Int,
) {
    Row(
        modifier =
            Modifier
                .clip(CircleShape)
                .clickable { }
                .background(
                    color = MainOnBackground,
                    shape = CircleShape,
                ).padding(
                    horizontal = 10.dp,
                    vertical = 5.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Text(text = title, color = MainAccent, fontSize = 14.sp)

        if (count > 0) {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    Modifier
                        .size(22.dp)
                        .background(color = MainSecondary.copy(0.8f), shape = CircleShape),
            ) {
                Text(
                    text = count.toString(),
                    color = MainPrimary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun TasksList(tasks: List<TaskDto>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(7.dp),
        modifier = Modifier.padding(top = 20.dp),
    ) {
        tasks.forEach { task ->
            TaskListItem(task = task)
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskListItem(task: TaskDto) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(color = MainSecondary, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 10.dp, horizontal = 15.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = task.category, fontSize = 12.sp, color = MainPrimary)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = task.title)
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "task info",
                    tint = MainPrimary,
                )
            }
        }
    }
}
