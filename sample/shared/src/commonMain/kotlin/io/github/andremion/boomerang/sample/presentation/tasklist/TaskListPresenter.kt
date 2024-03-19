package io.github.andremion.boomerang.sample.presentation.tasklist

import io.github.andremion.boomerang.AbsPresenter
import io.github.andremion.boomerang.sample.data.Task
import io.github.andremion.boomerang.sample.data.getTasks
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

class TaskListPresenter : AbsPresenter<TaskListUiState, TaskListUiEvent, TaskListUiEffect>(
    initialUiState = TaskListUiState.Initial
) {

    init {
        // TODO Move this out from init block
        onInit()
    }

    override fun onUiEvent(uiEvent: TaskListUiEvent) {
        when (uiEvent) {
            is TaskListUiEvent.CheckTaskClick -> onCheckTaskClick(uiEvent)
            is TaskListUiEvent.RemoveTaskClick -> onRemoveTaskClick(uiEvent)
        }
    }

    private fun onInit() {
        viewModelScope.launch {
            val tasks = getTasks()
            mutableUiState.update { state ->
                state.copy(tasks = tasks)
            }
        }
    }

    private fun onCheckTaskClick(uiEvent: TaskListUiEvent.CheckTaskClick) {
        mutableUiState.update { state ->
            val updatedTasks = state.tasks.update(uiEvent.taskId, uiEvent.isChecked)
            if (updatedTasks.count(Task::isDone) == state.tasks.size) {
                uiEffectChannel.trySend(TaskListUiEffect.ShowCongratulations)
            }
            state.copy(tasks = updatedTasks)
        }
    }

    private fun onRemoveTaskClick(uiEvent: TaskListUiEvent.RemoveTaskClick) {
        mutableUiState.update { state ->
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
