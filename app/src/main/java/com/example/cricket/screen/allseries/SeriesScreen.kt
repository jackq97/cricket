package com.example.cricket.screen.allseries

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.series.SeriesData
import com.example.cricket.ui.composables.SeriesRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import java.text.SimpleDateFormat
import java.util.*

@Destination
@Composable
fun SeriesScreen( viewModel: SeriesViewModel = hiltViewModel()) {

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val state = viewModel.state

    if (state.error == null) {

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            state.series?.let { series ->

                Column(modifier = Modifier.fillMaxSize()) {

                    SwipeRefresh(
                        state = swipeRefreshState,
                        onRefresh = {
                            viewModel.onEvent(SeriesEvent.Refresh)
                        }) {

                        val groupedMonth = series.data.groupBy { it.startDate.month }

                        SeriesList(groupedMonth = groupedMonth)
                    }
                }
            }
        }
    }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {

            }
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SeriesList(groupedMonth: Map<Int , List<SeriesData>>) {

    LazyColumn() {
        groupedMonth.forEach { (date, seriesData) ->
            stickyHeader {

                val dateFmt = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

                Text(
                        text = dateFmt.format(seriesData.first().startDate),
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

