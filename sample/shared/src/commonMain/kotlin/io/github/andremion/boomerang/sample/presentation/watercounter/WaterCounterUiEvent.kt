package io.github.andremion.boomerang.sample.presentation.watercounter

sealed interface WaterCounterUiEvent {
    data object AddOneClick : WaterCounterUiEvent
    data object ClearClick : WaterCounterUiEvent
}
