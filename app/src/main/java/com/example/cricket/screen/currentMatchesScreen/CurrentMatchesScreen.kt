package com.example.cricket.screen.currentMatchesScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.ui.composables.SeriesInfoRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(viewModel: CurrentMatchesViewModel = hiltViewModel()) {

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val state = viewModel.state

    if(state.error == null) {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            state.currentMatches?.let { currentMatches ->

                Column(modifier = Modifier.fillMaxSize()) {

                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = {
                            viewModel.onEvent(CurrentMatchesEvent.Refresh)
                        }
                    ) {
                        LazyMatchesRow(matches = currentMatches.data)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}

@Composable
fun LazyMatchesRow(matches: List<CurrentData>){

    LazyColumn{

        items(items = matches) { data ->
            SeriesInfoRow(data = data)
        }
    }
}







