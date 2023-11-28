package io.github.andremion.boomerang.sample.android.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.andremion.boomerang.android.collectUiState
import io.github.andremion.boomerang.android.launchInitialUiEvent
import io.github.andremion.boomerang.android.onUiEffect
import io.github.andremion.boomerang.android.saveablePresenter
import io.github.andremion.boomerang.sample.data.Task
import io.github.andremion.boomerang.sample.presentation.tasklist.TaskListPresenter
import io.github.andremion.boomerang.sample.presentation.tasklist.TaskListUiEffect
import io.github.andremion.boomerang.sample.presentation.tasklist.TaskListUiEvent
import io.github.andremion.boomerang.sample.presentation.tasklist.TaskListUiState
import kotlinx.coroutines.launch

@Composable
fun TasksList(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState
) {

    saveablePresenter { TaskListPresenter() } collectUiState { presenter, uiState ->

        presenter.launchInitialUiEvent { TaskListUiEvent.Init }
            .onUiEffect { uiEffect ->
                when (uiEffect) {
                    TaskListUiEffect.ShowCongratulations -> {
                        presenter.presenterScope.launch {
                            snackbarHostState.showSnackbar("Congratulations! You've done all the tasks!")
                        }
                    }
                }
            }

        ScreenContent(
            modifier = modifier,
            uiState = uiState,
            onUiEvent = presenter::onUiEvent
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier,
    uiState: TaskListUiState,
    onUiEvent: (TaskListUiEvent) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {

        Text(
            text = "Daily Tasks",
            style = MaterialTheme.typography.headlineSmall
        )

        when {
            uiState.tasks.isEmpty() -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                LazyColumn {
                    items(
                        items = uiState.tasks,
                        key = Task::id
                    ) { task ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = task.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Checkbox(
                                checked = task.isDone,
                                onCheckedChange = { isChecked ->
                                    onUiEvent(
                                        TaskListUiEvent.CheckTaskClick(
                                            taskId = task.id,
                                            isChecked = isChecked
                                        )
                                    )
                                }
                            )
                            IconButton(
                                onClick = { onUiEvent(TaskListUiEvent.RemoveTaskClick(task.id)) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Remove task"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
