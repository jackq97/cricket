package com.example.cricket.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.composables.CurrentMatchesRow
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.utils.ApiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(viewModel: CurrentMatchesViewModel = hiltViewModel()) {

    when (val result = viewModel.response.value){

        is ApiState.Success<*> -> {
            val allMatches = result.data as List<CurrentData>
            val isRefreshing = viewModel.isRefreshing.collectAsState().value
            val currentTime = viewModel.currentTime.collectAsState().value
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

            Surface(modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colors.background) {

                Column(modifier = Modifier.fillMaxSize()) {

                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = viewModel::refresh,
                        modifier = Modifier
                            .fillMaxSize()) {

                        LazyMatchesRow(matches = allMatches)
                    }
                }
            }
        }

        is ApiState.Loading -> {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }

        is ApiState.Empty -> {}

        is ApiState.Failure -> {}

    }
}


@Composable
fun LazyMatchesRow(matches: List<CurrentData>){

    LazyColumn{

        items(items = matches) { data ->
            CurrentMatchesRow(data = data)
        }
    }
}








