package com.example.cricket.screen.seriesinfoscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricket.model.seriesinfo.Match
import com.example.cricket.ui.composables.SeriesInfoRow
import com.example.cricket.ui.composables.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SeriesInfoScreen(id: String,
                     viewModel: SeriesInfoViewModel = hiltViewModel(),
                     navigator: DestinationsNavigator) {

    val state = viewModel.state

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column {

            state.seriesInfo?.let { seriesInfo ->

             TopBar(text = seriesInfo.data.info.name, destinationsNavigator = navigator)

                if (state.error == null) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {

                        LazySeriesInfoRow(seriesInfo = seriesInfo.data.matchList)

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
fun LazySeriesInfoRow(seriesInfo: List<Match>){

    LazyColumn {

        items(items = seriesInfo) { matchInfo ->
            SeriesInfoRow(
                seriesInfoData = matchInfo
            )
        }
    }
}

