package com.htueko.thesilverscreen.presentation.view.nowplaying

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.htueko.thesilverscreen.presentation.view.component.ConnectivityStatus
import com.htueko.thesilverscreen.presentation.view.component.ErrorStatus
import com.htueko.thesilverscreen.presentation.view.component.MovieCardComponent
import com.htueko.thesilverscreen.presentation.view.nowplaying.viewmodel.NowPlayingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun NowPlayingScreen(
    navController: NavController,
    viewmodel: NowPlayingViewModel = hiltViewModel()
) {
    val state = viewmodel.uiState.value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ConnectivityStatus()
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(10.dp)
            ) {
                items(state.movies) { data ->
                    MovieCardComponent(data, navController)
                }
            }
        }
        if (state.hasError){
            ErrorStatus(hasError = state.hasError, errorMessage = state.errorMessage)
        }

    }
}
