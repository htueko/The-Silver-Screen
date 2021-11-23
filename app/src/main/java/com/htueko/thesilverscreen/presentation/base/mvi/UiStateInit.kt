package com.htueko.thesilverscreen.presentation.base.mvi

/**
 * to initialize the starting state of the UiState
 */
interface Init<State> {
    fun initialize(): State
}
