package com.example.cricket

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cricket.screen.NavGraphs
import com.example.cricket.ui.composables.BottomBar
import com.example.cricket.ui.composables.TopBar
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun MainApp(){

    val navController = rememberNavController()

    Scaffold(

        topBar = {  },
        bottomBar = { BottomBar(navController = navController) },
        content = {
            DestinationsNavHost(
                navController = navController,
                navGraph = NavGraphs.root,
                modifier = Modifier.padding(it),
            )
        })
}