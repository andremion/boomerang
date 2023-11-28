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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.andremion.boomerang.android.collectUiState
import io.github.andremion.boomerang.android.launchInitialUiEvent
import io.github.andremion.boomerang.android.onUiEffect
import io.github.andremion.boomerang.android.saveablePresenter
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterPresenter
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiEffect
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiEvent
import io.github.andremion.boomerang.sample.presentation.watercounter.WaterCounterUiState
import kotlinx.coroutines.launch

@Composable
fun WaterCounter(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState
) {

    saveablePresenter { WaterCounterPresenter() } collectUiState { presenter, uiState ->

        presenter.launchInitialUiEvent { WaterCounterUiEvent.Init }
            .onUiEffect { uiEffect ->
                when (uiEffect) {
                    WaterCounterUiEffect.ShowCongratulations -> {
                        presenter.presenterScope.launch {
                            snackbarHostState.showSnackbar("Congratulations! You've taken ${uiState.count} glasses of water today!")
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
