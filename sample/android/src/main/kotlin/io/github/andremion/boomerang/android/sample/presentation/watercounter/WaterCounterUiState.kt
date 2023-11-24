package io.github.andremion.boomerang.android.sample.presentation.watercounter

data class WaterCounterUiState(val count: Int) {
    companion object {
        val Initial = WaterCounterUiState(count = 0)
    }
}
