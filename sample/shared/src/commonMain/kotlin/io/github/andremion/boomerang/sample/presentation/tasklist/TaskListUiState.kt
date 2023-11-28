package io.github.andremion.boomerang.sample.presentation.tasklist

import io.github.andremion.boomerang.sample.data.Task

data class TaskListUiState(val tasks: List<Task>) {
    companion object {
        val Initial = TaskListUiState(tasks = emptyList())
    }
}
