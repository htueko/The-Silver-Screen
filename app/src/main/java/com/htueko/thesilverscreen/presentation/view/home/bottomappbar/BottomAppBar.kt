package com.htueko.thesilverscreen.presentation.view.home.bottomappbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.htueko.thesilverscreen.R
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomAppBarScreen.NowPlaying
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomAppBarScreen.Popular
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomAppBarScreen.TopRated
import com.htueko.thesilverscreen.presentation.view.home.bottomappbar.BottomAppBarScreen.Upcoming

@Composable
fun BottomAppBar(navHostController: NavHostController) {
    // list of screen that will show on the bottom app bar
    val screens = listOf(
        NowPlaying,
        Popular,
        Upcoming,
        TopRated,
    )
    // to get the state of the backstack
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    // to get the current NavDestination
    val currentDestination: NavDestination? = navBackStackEntry?.destination
    //  bottom navigation
    BottomNavigation {
        // add bottom appbar items
        screens.forEach { screen ->
            AddBottomAppBarItem(
                screen = screen,
                currentDestination = currentDestination,
                navHostController = navHostController
            )
        }
    }
}

// extension function to add bottom appbar item
@Composable
fun RowScope.AddBottomAppBarItem(
    screen: BottomAppBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    // item on the bottom app bar
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(id = R.string.text_nav_icon)
            )
        },
        selected = currentDestination?.hierarchy?.any { navDestination ->
            navDestination.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.route) {
                // to remove from back stack
                popUpTo(navHostController.graph.findStartDestination().id)
                // to avoid multiple copy of same destination when reselected same item
                launchSingleTop = true
            }
        }
    )
}
