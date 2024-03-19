package io.github.andremion.boomerang.sample.presentation.tasklist

sealed interface TaskListUiEvent {
    data class CheckTaskClick(val taskId: Long, val isChecked: Boolean) : TaskListUiEvent
    data class RemoveTaskClick(val taskId: Long) : TaskListUiEvent
}
