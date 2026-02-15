package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.glindaquint.everwell.network.dto.tasks.InsertTaskRequest
import ru.glindaquint.everwell.services.TasksService
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
    @Inject
    constructor(
        private val tasksService: TasksService,
    ) : ViewModel() {
        val allTasks = tasksService.userTasks

        fun pushTask(task: InsertTaskRequest) {
            viewModelScope.launch(Dispatchers.IO) {
                tasksService.pushTask(task)
            }
        }
    }
