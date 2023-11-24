package io.github.andremion.boomerang

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Observes the [UiEffect] of the [Presenter] and calls the [block] whenever a new value is emitted.
 */
fun <UiEffect> Presenter<*, *, UiEffect>.onUiEffect(
    block: (UiEffect) -> Unit
) {
    uiEffect
        .onEach(block)
        .launchIn(presenterScope)
}
