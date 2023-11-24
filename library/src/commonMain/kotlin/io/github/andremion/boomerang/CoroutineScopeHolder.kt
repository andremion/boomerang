package io.github.andremion.boomerang

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeHolder {
    var presenterScope: CoroutineScope
}
