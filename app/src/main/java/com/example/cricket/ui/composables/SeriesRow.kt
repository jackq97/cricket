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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.cricket.model.previewparameter.SeriesPreviewParameterProvider
import com.example.cricket.model.series.SeriesData
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable()
fun SeriesRow(seriesData: SeriesData) {

    Column(modifier = Modifier
        .height(IntrinsicSize.Max)
        .fillMaxWidth()
        .padding(12.dp),
       // .clickable { navigator.navigate(SeriesInfoScreenDestination(id = seriesData.id)) },
        horizontalAlignment = Alignment.Start,

        ) {

        Text(text = seriesData.name,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(text = "${seriesData.startDate} - ${seriesData.startDate}",
            color = Color.DarkGray
        )
    }

    Divider(modifier = Modifier.fillMaxWidth(),
        color = Color.Black)
}



