package com.htueko.thesilverscreen.presentation.view.moviedetail.state

import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.presentation.base.mvi.Init
import com.htueko.thesilverscreen.presentation.base.mvi.LoadingState
import com.htueko.thesilverscreen.presentation.base.mvi.UiEvent
import com.htueko.thesilverscreen.presentation.base.mvi.UiSideEffect
import com.htueko.thesilverscreen.presentation.base.mvi.UiState

data class DetailUiState(
    val loading: LoadingState = LoadingState.IDLE,
    val movieDetail: MovieDetail? = null,
    val hasError: Boolean = false,
    val errorMessage: String? = null
) : UiState

sealed class DetailUiEvent : UiEvent {
    data class LoadMovieDetail(val id: Int) : DetailUiEvent()
}

sealed class DetailSideEffect : UiSideEffect {
    object ShowError : DetailSideEffect()
}

class DetailUiStateInit : Init<DetailUiState> {
    override fun initialize(): DetailUiState {
        return DetailUiState()
    }
}
