package io.github.andremion.boomerang

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Abstract presenter class that:
 * - Holds a [UiState] to be consumed by the UI and updated by the concrete implementation.
 * - Handles [UiEvent] from the UI.
 * - Holds [UiEffect] to be consumed by the UI and emitted by the concrete implementation.
 */
abstract class AbsPresenter<UiState, UiEvent, UiEffect>(initialUiState: UiState) :
    ViewModel(),
    Presenter<UiState, UiEvent, UiEffect> {

    protected val mutableUiState = MutableStateFlow(initialUiState)
    override val uiState: StateFlow<UiState> = mutableUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = initialUiState
    )

    protected val uiEffectChannel = Channel<UiEffect>()
    override val uiEffect: Flow<UiEffect> = uiEffectChannel.receiveAsFlow()
}
