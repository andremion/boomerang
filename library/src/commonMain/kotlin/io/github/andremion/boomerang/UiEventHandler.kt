package io.github.andremion.boomerang

/**
 * Contract for a component that handles [UiEvent] from the UI.
 */
interface UiEventHandler<UiEvent> {
    fun onUiEvent(uiEvent: UiEvent)
}
