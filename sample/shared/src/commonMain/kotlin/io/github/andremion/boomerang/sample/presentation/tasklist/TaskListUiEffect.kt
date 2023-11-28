package io.github.andremion.boomerang.sample.presentation.tasklist

sealed interface TaskListUiEffect {
    data object ShowCongratulations : TaskListUiEffect
}
