package io.github.andremion.boomerang

/**
 * Contract for the abstract presenter.
 */
interface Presenter<UiState, UiEvent, UiEffect> :
    UiStateHolder<UiState>,
    UiEventHandler<UiEvent>,
    UiEffectHolder<UiEffect>
