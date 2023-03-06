package com.example.cricket.screen.currentMatchesScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.ui.composables.CurrentMatchesRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination(route = "main_feed")
@Composable
fun HomeScreen(viewModel: CurrentMatchesViewModel = hiltViewModel(), navigator: DestinationsNavigator) {

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val state = viewModel.state

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        Column {

            TopAppBar {
                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Cricket",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp
                )
            }

            if (state.error == null) {

                state.currentMatches?.let { currentMatches ->

                    Column(modifier = Modifier.fillMaxSize()) {

                        SwipeRefresh(
                            state = swipeRefreshState,
                            onRefresh = {
                                viewModel.onEvent(CurrentMatchesEvent.Refresh)
                            }
                        ) {
                            LazyMatchesRow(matches = currentMatches.data, navigator = navigator)
                        }
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
fun LazyMatchesRow(matches: List<CurrentData>, navigator: DestinationsNavigator){

    LazyColumn{

        items(items = matches) { data ->
            CurrentMatchesRow(data = data, navigator = navigator)
        }
    }
}







