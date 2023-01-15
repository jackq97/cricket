package com.example.cricket.screen.allmatchesscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.allmatches.AllData
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.ui.composables.AllMatchesRow
import com.example.cricket.ui.composables.CustomRadioGroup
import com.example.cricket.ui.composables.RecentMatchesRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MatchesScreen(allMatchesViewModel: AllMatchesViewModel = hiltViewModel()) {


    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = allMatchesViewModel.state.isRefreshing
    )

    var selectedRadioOption by remember {
        mutableStateOf("Upcoming")
    }

    val state = allMatchesViewModel.state

    if (state.error == null) {

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CustomRadioGroup() {
                        selectedRadioOption = it
                    }

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        allMatchesViewModel.onEvent(AllMatchesEvent.Refresh)
                    }
                ) {

                    if (selectedRadioOption == "Upcoming") {

                        state.allMatches?.let {

                            LazyAllMatchesRow(matches = it.data)
                        }


                    } else {

                        state.currentMatches?.let { currentMatches ->

                            LazyRecentMatchesRow(matches = currentMatches.data)
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
fun LazyAllMatchesRow(matches: List<AllData>){

    LazyColumn(modifier = Modifier) {

        items(items = matches) { data ->
            AllMatchesRow(allMatchesData = data)
        }
    }
}

@Composable
fun LazyRecentMatchesRow(matches: List<CurrentData>){

    LazyColumn(modifier = Modifier) {

        items(items = matches) { data ->
            RecentMatchesRow(data = data)
        }
    }
}
