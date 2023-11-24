package io.github.andremion.boomerang.android.sample.data

import kotlinx.coroutines.delay

suspend fun getTasks(): List<Task> {
    delay(500) // Simulate long running operation
    return List(30) { index ->
        Task(
            id = index,
            name = "Task # $index",
            isDone = false
        )
    }
}
