package io.github.andremion.boomerang.sample

import io.github.andremion.boomerang.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun <UiState, UiEvent, UiEffect> Presenter<UiState, UiEvent, UiEffect>.uiStateFlow(): CFlow<UiState> {
    presenterScope = CoroutineScope(Dispatchers.Main)
    return CFlow(uiState)
}

fun <UiState, UiEvent, UiEffect> Presenter<UiState, UiEvent, UiEffect>.uiEffectFlow(): CFlow<UiEffect> =
    CFlow(uiEffect)
