package com.example.cricket.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.composables.AllMatchesRow
import com.example.cricket.composables.CustomRadioGroup
import com.example.cricket.composables.RecentMatchesRow
import com.example.cricket.model.allmatches.AllData
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.utils.ApiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MatchesScreen(allMatchesViewModel: AllMatchesViewModel = hiltViewModel()){

    when (val result = allMatchesViewModel.response.value){

        is ApiState.Success<*> -> {

            val allMatches = result.data as List<AllData>

            val isRefreshing = allMatchesViewModel.isRefreshing.collectAsState().value

            val currentTime = allMatchesViewModel.currentTime.collectAsState().value

            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

            var selectedRadioOption by remember {
                mutableStateOf("Upcoming")
            }

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colors.background,
                ) {

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = allMatchesViewModel::refresh,
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                        CustomRadioGroup(){
                            selectedRadioOption = it
                        }

                        if (selectedRadioOption == "Upcoming") {

                            LazyAllMatchesRow(matches = allMatches )

                        } else {

                            when(val allResult = allMatchesViewModel.currentMatchResponse.value){

                                is ApiState.Success<*> -> {

                                    val currentMatches = allResult.data

                                    LazyRecentMatchesRow(matches = currentMatches as List<CurrentData>)
                                }

                                is ApiState.Loading -> {
                                    Column(verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally) {
                                        CircularProgressIndicator()
                                    }
                                }

                                is ApiState.Failure -> {

                                }

                                is ApiState.Empty -> {

                                }
                            }
                        }
                    }
                }
            }
        }

        is ApiState.Loading -> {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
            }
        }

        is ApiState.Empty -> {

        }

        is ApiState.Failure -> {

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
