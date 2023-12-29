package io.github.andremion.boomerang.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import io.github.andremion.boomerang.Presenter
import io.github.andremion.boomerang.onUiEffect

/**
 * Collects the [UiState] of the [Presenter] and calls the [block] whenever a new value is emitted.
 */
@Composable
inline infix fun <UiState, UiEvent, UiEffect> Presenter<UiState, UiEvent, UiEffect>.collectUiState(
    block: @Composable (Presenter<UiState, UiEvent, UiEffect>, UiState) -> Unit
) {
    val state by uiState.collectAsState()
    block(this, state)
}

/**
 * Launches the initial [UiEvent] of the [Presenter].
 * Using [rememberSaveable] to prevent launching the initial [UiEvent] multiple times for the same [Presenter]
 * on recompositions and configuration changes.
 */
@Composable
fun <UiEvent, UiEffect> Presenter<*, UiEvent, UiEffect>.launchInitialUiEvent(
    block: () -> UiEvent
): Presenter<*, UiEvent, UiEffect> {
    rememberSaveable(key = "${hashCode()}[launchInitialUiEvent]") {
        onUiEvent(block())
        // We are not interested in remembering something.
        // We are only interested in call this init block once per recompositions and configuration changes.
        false
    }
    return this
}

/**
 * Observes the [UiEffect] of the [Presenter] and calls the [block] whenever a new value is emitted.
 * Using [rememberSaveable] to prevent registering multiple observers for the same [Presenter]
 * on recompositions and configuration changes.
 */
@Composable
fun <UiEffect> Presenter<*, *, UiEffect>.onUiEffect(
    block: (UiEffect) -> Unit
) {
    rememberSaveable(this, key = "${hashCode()}[onUiEffect]") {
        onUiEffect(block)
        // We are not interested in remembering something.
        // We are only interested in call this init block once per recompositions and configuration changes.
        false
    }
}
