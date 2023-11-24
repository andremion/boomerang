package io.github.andremion.boomerang

import kotlinx.coroutines.flow.StateFlow

/**
 * Contract for a component that holds a [UiState] to be consumed by the UI.
 */
interface UiStateHolder<UiState> {
    val uiState: StateFlow<UiState>
}
