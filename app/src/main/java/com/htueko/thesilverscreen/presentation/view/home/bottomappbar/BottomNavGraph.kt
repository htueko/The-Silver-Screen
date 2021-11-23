package com.htueko.thesilverscreen.presentation.view.home.bottomappbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.htueko.thesilverscreen.presentation.view.moviedetail.MovieDetailScreen
import com.htueko.thesilverscreen.presentation.view.nowplaying.NowPlayingScreen
import com.htueko.thesilverscreen.presentation.view.popular.PopularScreen
import com.htueko.thesilverscreen.presentation.view.toprated.TopRatedScreen
import com.htueko.thesilverscreen.presentation.view.upcoming.UpComingScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomAppBarScreen.NowPlaying.route
    ) {
        // now playing screen
        composable(route = BottomAppBarScreen.NowPlaying.route) {
            NowPlayingScreen(navHostController)
        }
        // popular screen
        composable(route = BottomAppBarScreen.Popular.route) {
            PopularScreen(navHostController)
        }
        // upcoming screen
        composable(route = BottomAppBarScreen.Upcoming.route) {
            UpComingScreen(navHostController)
        }
        // top rated screen
        composable(route = BottomAppBarScreen.TopRated.route) {
            TopRatedScreen(navHostController)
        }
        // to detail screen
        composable(
            route = Screen.Detail.route + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let { id ->
                MovieDetailScreen(id.toInt())
            }
        }
    }
}
