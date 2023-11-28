package io.github.andremion.boomerang.sample.presentation.watercounter

sealed interface WaterCounterUiEffect {
    data object ShowCongratulations: WaterCounterUiEffect
}
