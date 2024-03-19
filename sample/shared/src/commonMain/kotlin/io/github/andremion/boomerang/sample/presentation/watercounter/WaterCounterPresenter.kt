package io.github.andremion.boomerang.sample.presentation.watercounter

import io.github.andremion.boomerang.AbsPresenter
import kotlinx.coroutines.flow.update

private const val SUCCESS_COUNT = 10

class WaterCounterPresenter : AbsPresenter<WaterCounterUiState, WaterCounterUiEvent, WaterCounterUiEffect>(
    initialUiState = WaterCounterUiState.Initial
) {

    override fun onUiEvent(uiEvent: WaterCounterUiEvent) {
        when (uiEvent) {
            WaterCounterUiEvent.AddOneClick -> onAddOneClick()
            WaterCounterUiEvent.ClearClick -> onClearClick()
        }
    }

    private fun onAddOneClick() {
        mutableUiState.update { state ->
            val count = state.count + 1
            if (count == SUCCESS_COUNT) {
                uiEffectChannel.trySend(WaterCounterUiEffect.ShowCongratulations)
            }
            state.copy(count = count)
        }
    }

    private fun onClearClick() {
        mutableUiState.update { state ->
            state.copy(count = 0)
        }
    }
}
