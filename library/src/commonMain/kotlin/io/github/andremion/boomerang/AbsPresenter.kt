package io.github.andremion.boomerang

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * Abstract presenter class that:
 * - Holds a [UiState] to be consumed by the UI and updated by the concrete implementation.
 * - Handles [UiEvent] from the UI.
 * - Holds [UiEffect] to be consumed by the UI and emitted by the concrete implementation.
 * - Holds a [CoroutineScope] that should be defined at the time of the presenter injection
 * and can be used by the concrete implementation or the UI.
 */
abstract class AbsPresenter<UiState, UiEvent, UiEffect>(initialUiState: UiState) :
    Presenter<UiState, UiEvent, UiEffect> {

    final override lateinit var presenterScope: CoroutineScope

    private val mutableUiState = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<UiState> = mutableUiState

    private val mutableUiEffect = MutableSharedFlow<UiEffect>(
        // We need to buffer to allow emitting effects out of a suspend function.
        extraBufferCapacity = 1
    )
    override val uiEffect: MutableSharedFlow<UiEffect> = mutableUiEffect

    protected fun updateUiState(block: (UiState) -> UiState) {
        mutableUiState.update(block)
    }
}
