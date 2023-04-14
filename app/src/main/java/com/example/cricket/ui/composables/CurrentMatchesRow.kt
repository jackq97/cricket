package com.example.cricket.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cricket.R
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.model.previewparameter.CurrentMatchesDataPreviewParameterProvider
import com.example.cricket.screen.destinations.SeriesInfoScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CurrentMatchesRow(data: CurrentData, navigator: DestinationsNavigator) {

    // TODO: make this a function

    val dateTime = data.dateTimeGMT!!
    val timeFmt = SimpleDateFormat("hh:mm", Locale.getDefault())

    val sourceTimeZone = TimeZone.getTimeZone("GMT") // the current timezone of the date object
    val destinationTimeZone = TimeZone.getDefault() // the timezone you want to convert to (IST)

    val sourceOffset = sourceTimeZone.getOffset(dateTime.time)
    val destinationOffset = destinationTimeZone.getOffset(dateTime.time)
    val adjustedTime = dateTime.time + (destinationOffset - sourceOffset)
    val adjustedDate = Date(adjustedTime)

    Column(modifier = Modifier
        .height(135.dp)
        .fillMaxWidth()
        .padding(
            start = 12.dp,
            end = 12.dp,
            top = 12.dp,
            bottom = 8.dp
        ),
        horizontalAlignment = Alignment.Start,
    ) {

        Row(modifier = Modifier.height(20.dp),
            horizontalArrangement = Arrangement.End
            ) {

            Text(modifier = Modifier,
                text = if(data.matchType != null){data.matchType.replaceFirstChar { it.uppercase() }} else {"?"},
                fontWeight = FontWeight.Light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

            Text(modifier = Modifier.width(320.dp),
                text = " • ${data.name}",
                fontWeight = FontWeight.Light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

            //Spacer(modifier = Modifier.width(200.dp))

            val currentTimeMillis = System.currentTimeMillis()

            if (currentTimeMillis < dateTime.time)
                IconButton(modifier = Modifier,
                    onClick = { /*TODO*/ }) {

                    Icon(
                        painter = painterResource(id = R.drawable.outline_notifications_24),
                        contentDescription = "set reminder"
                    )
                }
        }
        
        Row(modifier = Modifier
            .padding(top = 3.dp)) {

            if(data.teamInfo != null)
            //coil
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.teamInfo[0].img)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(20.dp)
                        .width(40.dp)
                        .clip(shape = RectangleShape)
                )

            else
                Image(
                    painter = painterResource(id = R.drawable.fl_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(20.dp)
                        .width(40.dp)
                        .clip(shape = RectangleShape)
                )

            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 3.dp),
                text = if(data.teamInfo != null && data.teamInfo[0].shortname != null){data.teamInfo[0].shortname}
                else{data.teams[0]},
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

            if (data.score.isNotEmpty())
                Text(modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = "${data.score[0].r}-${data.score[0].w} (${data.score[0].o})",
                    fontWeight = FontWeight.Bold
                )
        }

        Row(modifier = Modifier
            .padding(top = 5.dp)) {

            if (data.teamInfo != null && data.teamInfo.size > 1)
            //coil
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.teamInfo[1].img)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(20.dp)
                    .width(40.dp)
                    .clip(shape = RectangleShape)
            )

            else {
                Image(
                    painter = painterResource(id = R.drawable.fl_default),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(20.dp)
                        .width(40.dp)
                        .clip(shape = RectangleShape)
                )
            }

            Text(modifier = Modifier
                .weight(1f)
                .padding(start = 3.dp),
                text = if(data.teamInfo[1].shortname != null){data.teamInfo[1].shortname}
                else{data.teams[1]},
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

            if(data.score.size > 1)
                Text(modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = "${data.score[1].r}-${data.score[1].w} (${data.score[1].o})",
                    fontWeight = FontWeight.Bold)
        }

        Text(modifier = Modifier
            .padding(5.dp),
            text = data.status,
            color = Color(0xFF7AC4EB)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {

            Text(text = timeFmt.format(adjustedTime))

            Spacer(modifier = Modifier.width(250.dp))

            Column(modifier = Modifier
                .width(80.dp)
                .background(color = Color.LightGray, shape = RectangleShape),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(modifier = Modifier.clickable { navigator.navigate(SeriesInfoScreenDestination(data.series_id)) },
                    text = "SCHEDULE"
                )
            }

        }
    }

}
@Preview(showBackground = true)
@Composable
fun MatchesRowPreview(@PreviewParameter(CurrentMatchesDataPreviewParameterProvider::class) data: CurrentData){

    CurrentMatchesRow(data = data, navigator = EmptyDestinationsNavigator)
}