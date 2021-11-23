package com.htueko.thesilverscreen.presentation.view.moviedetail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.htueko.thesilverscreen.R
import com.htueko.thesilverscreen.domain.model.MovieDetail
import com.htueko.thesilverscreen.presentation.base.mvi.LoadingState
import com.htueko.thesilverscreen.presentation.view.component.BodyTextComponent
import com.htueko.thesilverscreen.presentation.view.component.ConnectivityStatus
import com.htueko.thesilverscreen.presentation.view.component.DividerTiny
import com.htueko.thesilverscreen.presentation.view.component.HorizontalSpacerTiny
import com.htueko.thesilverscreen.presentation.view.component.TitleTextComponent
import com.htueko.thesilverscreen.presentation.view.moviedetail.state.DetailUiEvent
import com.htueko.thesilverscreen.presentation.view.moviedetail.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable
fun MovieDetailScreen(
    id: Int,
    viewmodel: MovieDetailViewModel = hiltViewModel()
) {

    viewmodel.setEvent(DetailUiEvent.LoadMovieDetail(id))
    val state = viewmodel.uiState.collectAsState().value
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        state = scrollState
    ) {
        item {
            ConnectivityStatus()
            Loader(loadingState = state.loading)
            ShowImage(
                movie = state.movieDetail,
                containerHeight = 200.dp
            )
            ShowMovieInfo(movie = state.movieDetail)
        }
    }
}

@Composable
fun Loader(loadingState: LoadingState) {
    if (loadingState == LoadingState.LOADING) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(hasError: Boolean, errorMessage: String? = null) {
    if (hasError) {
        Text(text = errorMessage!!)
    }
}

@Composable
fun ShowImage(movie: MovieDetail?, containerHeight: Dp) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            modifier = Modifier
                .heightIn(max = containerHeight)
                .fillMaxWidth(),
            painter = rememberImagePainter(data = MovieDetail.getBackDropImageUrl(movie?.backdropPath)),
            contentDescription = movie?.title,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ShowMovieInfo(movie: MovieDetail?) {
    HorizontalSpacerTiny()
    movie?.title?.let {
        TitleTextComponent(text = it)
        DividerTiny()
    }
    if (movie?.tagline != null && movie.tagline.isNotEmpty()) {
        BodyTextComponent(text = movie.tagline)
        DividerTiny()
    }
    movie?.overview?.let {
        BodyTextComponent(text = it)
        DividerTiny()
    }
    movie?.voteAverage?.let {
        Row {
            BodyTextComponent(text = stringResource(id = R.string.text_average_rating))
            BodyTextComponent(text = it.toString())
        }
        DividerTiny()
    }
    movie?.runtime?.let {
        val hour = it / 60
        val minutes = it % 60
        Row {
            BodyTextComponent(text = stringResource(id = R.string.text_runtime))
            BodyTextComponent(text = "$hour hour : $minutes minutes")
        }
        DividerTiny()
    }
    GenreGroup(movie = movie)
    DividerTiny()
    BodyTextComponent(text = stringResource(id = R.string.text_language))
    LanguageGroup(movie = movie)
    DividerTiny()
    BodyTextComponent(text = stringResource(id = R.string.text_company))
    CompanyGroup(movie = movie)
    DividerTiny()
}

@Composable
fun GenreGroup(movie: MovieDetail?) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 16.dp,
    ) {
        movie?.genres?.forEach { it ->
            CustomChip(text = it.name)
        }
    }
}

@Composable
fun LanguageGroup(movie: MovieDetail?) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 16.dp,
    ) {
        movie?.spokenLanguages?.forEach { it ->
            CustomChip(text = it.name)
        }
    }
}

@Composable
fun CompanyGroup(movie: MovieDetail?) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 16.dp,
    ) {
        movie?.productionCompanies?.forEach { it ->
            CustomChip(text = it.name)
        }
    }
}

@Composable
fun CustomChip(text: String) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.DarkGray
        )
    ) {
        Row(modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp)) {
            Text(text = text)
        }
    }
}
