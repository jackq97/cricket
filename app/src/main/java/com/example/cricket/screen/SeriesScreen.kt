package com.example.cricket.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.series.SeriesData
import com.example.cricket.utils.ApiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SeriesScreen(seriesViewModel: SeriesViewModel = hiltViewModel()){

    when (val result = seriesViewModel.response.value){

        is ApiState.Success<*> -> {
            val allSeries = result.data as List<SeriesData>
            val isRefreshing = seriesViewModel.isRefreshing.collectAsState().value
            val currentTime = seriesViewModel.currentTime.collectAsState().value
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

            Surface(modifier = Modifier
                .fillMaxSize(),
                color = Color.Red
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    val grouped = allSeries.groupBy { it.startDate.time }
                    //val grouped = allSeries.startDate.groupBy { it.dec() }

                    Log.d("sroted values", "SeriesScreen: $grouped")

                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = seriesViewModel::refresh,
                        modifier = Modifier
                            .fillMaxSize()) {


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

    /*when (val result = seriesViewModel.response.value){

        is ApiState.Success<*> -> {

            val allSeries = result.data as List<SeriesData>

            //val dates = allSeries.map { it.startDate }

            val isRefreshing = seriesViewModel.isRefreshing.collectAsState().value

            val currentTime = seriesViewModel.currentTime.collectAsState().value

            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

            val grouped = allSeries.groupBy { it.startDate.time }
            //val grouped = allSeries.startDate.groupBy { it.dec() }

            Log.d("sroted values", "SeriesScreen: ${grouped}")

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    SwipeRefresh(

                        state = swipeRefreshState,
                        onRefresh = seriesViewModel::refresh,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        //SeriesList(grouped = grouped)
                    }
                }
            }
        }

        is ApiState.Loading -> {

            Surface(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                }
            }
        }

        is ApiState.Empty -> {

        }

        is ApiState.Failure -> {

        }

    }*/


/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SeriesList(grouped: Map<Long, List<SeriesData>>) {

    LazyColumn {
        grouped.forEach { (date, seriesData) ->
            stickyHeader {
                Text(
                    text = "$date",
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }

            items(items = seriesData) { data ->
                SeriesRow(seriesData = data)
            }
        }
    }
}


*/

