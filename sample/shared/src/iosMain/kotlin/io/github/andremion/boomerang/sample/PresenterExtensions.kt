package io.github.andremion.boomerang.sample

import io.github.andremion.boomerang.Presenter

fun <UiState, UiEvent, UiEffect> Presenter<UiState, UiEvent, UiEffect>.uiStateFlow(): CFlow<UiState> =
    CFlow(uiState)

fun <UiState, UiEvent, UiEffect> Presenter<UiState, UiEvent, UiEffect>.uiEffectFlow(): CFlow<UiEffect> =
    CFlow(uiEffect)
