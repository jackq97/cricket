package com.example.cricket.screen.seriesinfoscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel

@Destination
@Composable
fun SeriesInfoScreen(id: String, viewModel: SeriesInfoViewModel = hiltViewModel()) {

    val state = viewModel.state
    if (state.error == null) {

        androidx.compose.material.Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = androidx.compose.material.MaterialTheme.colors.background
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            state.seriesInfo?.let { seriesInfo ->

                Log.d("Seriesi name", "SeriesInfoScreen: ${seriesInfo.data.info.name}")

                Text(
                    text = seriesInfo.data.info.name,
                color = Color.Black)
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
                color = androidx.compose.material.MaterialTheme.colors.error
            )
        }
    }
}