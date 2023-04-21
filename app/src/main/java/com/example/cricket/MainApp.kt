package com.example.cricket

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cricket.screen.NavGraphs
import com.example.cricket.ui.composables.BottomBar
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun MainApp(){

    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    // State of topBar, set state to false, if current page route is "car_details"
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    val navController = rememberNavController()

    // Subscribe to navBackStackEntry, required to get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // Control TopBar and BottomBar
    when (navBackStackEntry?.destination?.route) {

        "main_feed" -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        "matches_screen" -> {
            bottomBarState.value = true
            topBarState.value = false
        }

        "series_screen" -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        "series_info_screen/{id}" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
            topBarState.value = true
        }
    }

    Scaffold(
        topBar = {  },
        bottomBar = {
            val density = LocalDensity.current
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically {
                    // Slide in from 40 dp from the top.
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ),
                exit = slideOutVertically() + shrinkVertically()
            ) {
                BottomBar(navController = navController)
            } },
        content = {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                modifier = Modifier.padding(it),
            )
        })
}