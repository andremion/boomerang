package io.github.andremion.boomerang.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.andremion.boomerang.Presenter

class SaveablePresenter<UiState, UiEvent, UiEffect> internal constructor(
    val presenter: Presenter<UiState, UiEvent, UiEffect>
) : ViewModel(), Presenter<UiState, UiEvent, UiEffect> by presenter {

    init {
        presenter.presenterScope = viewModelScope
    }

    override fun toString(): String = super.toString() + "[$presenter]"
}

@Composable
fun <UiState, UiEvent, UiEffect> saveablePresenter(
    initializer: () -> Presenter<UiState, UiEvent, UiEffect>
): SaveablePresenter<UiState, UiEvent, UiEffect> =
    viewModel(key = initializer::class.java.name) {
        SaveablePresenter(presenter = initializer())
    }
