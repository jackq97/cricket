package com.example.cricket.screen.allseries

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.R
import com.example.cricket.model.series.SeriesData
import com.example.cricket.ui.composables.SeriesRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.SimpleDateFormat
import java.util.*

@Destination
@Composable
fun SeriesScreen( viewModel: SeriesViewModel = hiltViewModel(), navigator: DestinationsNavigator) {

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val state = viewModel.state

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column {

            TopAppBar {
                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Series",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            }

            if (state.error == null) {

                state.series?.let { series ->

                    Column(modifier = Modifier.fillMaxSize()) {

                        SwipeRefresh(
                            state = swipeRefreshState,
                            onRefresh = {
                                viewModel.onEvent(SeriesEvent.Refresh)
                            }) {

                            val groupedMonth = series.data.groupBy { it.startDate?.month }

                            SeriesList(groupedMonth = groupedMonth, navigator = navigator)
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
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SeriesList(groupedMonth: Map<Int? , List<SeriesData>>, navigator: DestinationsNavigator) {

    LazyColumn {

        groupedMonth.forEach { (date, seriesData) ->

            stickyHeader {

                val dateFmt = SimpleDateFormat("MMMM", Locale.getDefault())

                Text(
                        text = dateFmt.format(seriesData.first().startDate),
                        color = Color.White,
                        modifier = Modifier
                            .background(colorResource(R.color.light_purple))
                            .padding(5.dp)
                            .fillMaxWidth()
                    )

            }

            items(items = seriesData) { data ->
                SeriesRow(seriesData = data, navigator = navigator)
            }
        }
    }
}

