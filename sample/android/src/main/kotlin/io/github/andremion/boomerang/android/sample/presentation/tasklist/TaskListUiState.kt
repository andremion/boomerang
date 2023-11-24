package io.github.andremion.boomerang.android.sample.presentation.tasklist

import io.github.andremion.boomerang.android.sample.data.Task

data class TaskListUiState(val tasks: List<Task>) {
    companion object {
        val Initial = TaskListUiState(tasks = emptyList())
    }
}
