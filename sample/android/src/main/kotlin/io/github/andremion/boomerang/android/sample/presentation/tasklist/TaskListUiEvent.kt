package io.github.andremion.boomerang.android.sample.presentation.tasklist

sealed interface TaskListUiEvent {
    data object Init : TaskListUiEvent
    data class CheckTaskClick(val taskId: Int, val isChecked: Boolean) : TaskListUiEvent
    data class RemoveTaskClick(val taskId: Int) : TaskListUiEvent
}
