package com.htueko.thesilverscreen.presentation.view.home.bottomappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomAppBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object NowPlaying : BottomAppBarScreen(
        route = "now",
        title = "Playing",
        icon = Icons.Default.Movie
    )

    object Popular : BottomAppBarScreen(
        route = "popular",
        title = "Popular",
        icon = Icons.Default.Movie
    )

    object Upcoming : BottomAppBarScreen(
        route = "upcoming",
        title = "Upcoming",
        icon = Icons.Default.Movie
    )

    object TopRated : BottomAppBarScreen(
        route = "top",
        title = "Top Rated",
        icon = Icons.Default.Movie
    )
}

sealed class Screen(
    val route: String,
    val title: String,
) {
    object Detail : Screen(
        route = "detail",
        title = "Detail"
    )
}
