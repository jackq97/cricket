package com.example.cricket.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cricket.model.series.SeriesData
import com.example.cricket.screen.destinations.SeriesInfoScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.SimpleDateFormat
import java.util.*

@Composable()
fun SeriesRow(seriesData: SeriesData, navigator: DestinationsNavigator) {

    Column(modifier = Modifier
        .height(IntrinsicSize.Max)
        .fillMaxWidth()
        .padding(12.dp)
        .clickable { navigator.navigate(SeriesInfoScreenDestination(id = seriesData.id)) },
        horizontalAlignment = Alignment.Start,

        ) {

        Text(text = seriesData.name,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        val timeFmt = SimpleDateFormat("MMM d", Locale.getDefault())

        Text(text = "${timeFmt.format(seriesData.startDate)} - ${seriesData.endDate}",
            color = Color.DarkGray
        )
    }
}



