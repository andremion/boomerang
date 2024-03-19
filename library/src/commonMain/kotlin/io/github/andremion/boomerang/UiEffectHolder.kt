package io.github.andremion.boomerang

import kotlinx.coroutines.flow.Flow

/**
 * Contract for a component that holds [UiEffect] to be consumed by the UI.
 */
interface UiEffectHolder<UiEffect> {
    val uiEffect: Flow<UiEffect>
}
