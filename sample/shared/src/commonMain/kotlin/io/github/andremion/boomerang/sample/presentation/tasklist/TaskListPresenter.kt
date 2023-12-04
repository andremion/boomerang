package io.github.andremion.boomerang.sample.presentation.tasklist

import io.github.andremion.boomerang.AbsPresenter
import io.github.andremion.boomerang.sample.data.Task
import io.github.andremion.boomerang.sample.data.getTasks
import kotlinx.coroutines.launch

class TaskListPresenter : AbsPresenter<TaskListUiState, TaskListUiEvent, TaskListUiEffect>(
    initialUiState = TaskListUiState.Initial
) {

    override fun onUiEvent(uiEvent: TaskListUiEvent) {
        when (uiEvent) {
            TaskListUiEvent.Init -> onInit()
            is TaskListUiEvent.CheckTaskClick -> onCheckTaskClick(uiEvent)
            is TaskListUiEvent.RemoveTaskClick -> onRemoveTaskClick(uiEvent)
        }
    }

    private fun onInit() {
        presenterScope.launch {
            val tasks = getTasks()
            updateUiState { state ->
                state.copy(tasks = tasks)
            }
        }
    }

    private fun onCheckTaskClick(uiEvent: TaskListUiEvent.CheckTaskClick) {
        updateUiState { state ->
            val updatedTasks = state.tasks.update(uiEvent.taskId, uiEvent.isChecked)
            if (updatedTasks.count(Task::isDone) == state.tasks.size) {
                uiEffect.tryEmit(TaskListUiEffect.ShowCongratulations)
            }
            state.copy(tasks = updatedTasks)
        }
    }

    private fun onRemoveTaskClick(uiEvent: TaskListUiEvent.RemoveTaskClick) {
        updateUiState { state ->
            val updatedTasks = state.tasks.filterNot { task -> task.id == uiEvent.taskId }
            state.copy(tasks = updatedTasks)
        }
    }
}

private fun List<Task>.update(
    taskId: Long,
    isChecked: Boolean
): List<Task> =
    map { task ->
        if (task.id == taskId) {
            task.copy(isDone = isChecked)
        } else {
            task
        }
    }
