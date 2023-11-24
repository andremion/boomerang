package io.github.andremion.boomerang.android.sample.presentation.watercounter

import io.github.andremion.boomerang.AbsPresenter

private const val SUCCESS_COUNT = 10

class WaterCounterPresenter : AbsPresenter<WaterCounterUiState, WaterCounterUiEvent, WaterCounterUiEffect>(
    initialUiState = WaterCounterUiState.Initial
) {

    override fun onUiEvent(uiEvent: WaterCounterUiEvent) {
        when (uiEvent) {
            is WaterCounterUiEvent.Init -> onInit()
            WaterCounterUiEvent.AddOneClick -> onAddOneClick()
            WaterCounterUiEvent.ClearClick -> onClearClick()
        }
    }

    private fun onInit() {
    }

    private fun onAddOneClick() {
        updateUiState { state ->
            val count = state.count + 1
            if (count == SUCCESS_COUNT) {
                uiEffect.tryEmit(WaterCounterUiEffect.ShowCongratulations)
            }
            state.copy(count = count)
        }
    }

    private fun onClearClick() {
        updateUiState { state ->
            state.copy(count = 0)
        }
    }
}
