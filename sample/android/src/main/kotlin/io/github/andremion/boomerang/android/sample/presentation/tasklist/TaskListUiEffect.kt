package io.github.andremion.boomerang.android.sample.presentation.tasklist

sealed interface TaskListUiEffect {
    data object ShowCongratulations : TaskListUiEffect
}
