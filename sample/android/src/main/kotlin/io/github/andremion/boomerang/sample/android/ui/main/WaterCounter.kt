package io.github.andremion.boomerang.sample.android.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterPresenter
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiEffect
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiEvent
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun WaterCounter(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState
) {
    val presenter = viewModel(WaterCounterPresenter::class) {
        WaterCounterPresenter() // Inject presenter from any DI framework accordingly
    }

    val uiState by presenter.uiState.collectAsStateWithLifecycle()

    ScreenContent(
        modifier = modifier,
        uiState = uiState,
        onUiEvent = presenter::onUiEvent
    )

    LaunchedEffect(presenter) {
        presenter.uiEffect.onEach { uiEffect ->
            when (uiEffect) {
                WaterCounterUiEffect.ShowCongratulations -> {
                    launch {
                        snackbarHostState.showSnackbar("Congratulations! You've taken ${uiState.count} glasses of water today!")
                    }
                }
            }
        }.launchIn(this)
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier,
    uiState: WaterCounterUiState,
    onUiEvent: (WaterCounterUiEvent) -> Unit
) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Water Counter",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(text = "You've had ${uiState.count} glasses of water today")
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Button(onClick = { onUiEvent(WaterCounterUiEvent.AddOneClick) }) {
                    Text(text = "Add one")
                }
                Button(onClick = { onUiEvent(WaterCounterUiEvent.ClearClick) }) {
                    Text(text = "Clear")
                }
            }
        }
    }
}
