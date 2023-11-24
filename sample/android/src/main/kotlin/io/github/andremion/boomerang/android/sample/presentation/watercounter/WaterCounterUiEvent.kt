package io.github.andremion.boomerang.android.sample.presentation.watercounter

sealed interface WaterCounterUiEvent {
    data object Init : WaterCounterUiEvent
    data object AddOneClick : WaterCounterUiEvent
    data object ClearClick : WaterCounterUiEvent
}
