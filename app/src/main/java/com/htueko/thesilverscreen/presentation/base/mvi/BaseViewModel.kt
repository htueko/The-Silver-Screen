package com.htueko.thesilverscreen.presentation.base.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState, SideEffect : UiSideEffect> :
    ViewModel() {

    // / to create the Initial State of View
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    // / to received the latest state of ui state
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    // / drop event if there is no subscriber
    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    // / single event to take action likes SingleLiveEvent
    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    // / to get the Current State of View
    val currentState: State
        get() = uiState.value

    init {
        subscribeUiEvents()
    }

    // / to start listening the Ui Events
    private fun subscribeUiEvents() {
        viewModelScope.launch {
            event.collect { handleEvent(it) }
        }
    }

    // / to handle each event
    abstract fun handleEvent(event: Event)

    // / to set new event
    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    // / to set new ui state
    protected fun setUiState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    // / to set new ui side effect
    protected fun setSideEffect(builder: () -> SideEffect) {
        val sideEffectValue = builder()
        viewModelScope.launch { _sideEffect.send(sideEffectValue) }
    }
}
