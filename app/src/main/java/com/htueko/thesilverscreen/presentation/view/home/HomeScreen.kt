package com.htueko.thesilverscreen.presentation.view.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomAppBar
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomNavGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
@Suppress("FunctionNaming")
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomAppBar(navHostController = navController) }
    ) {
        BottomNavGraph(navHostController = navController)
    }
}
