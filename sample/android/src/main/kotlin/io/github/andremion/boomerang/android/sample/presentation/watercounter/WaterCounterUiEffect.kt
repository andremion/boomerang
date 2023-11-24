package io.github.andremion.boomerang.android.sample.presentation.watercounter

sealed interface WaterCounterUiEffect {
    data object ShowCongratulations: WaterCounterUiEffect
}
