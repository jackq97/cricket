package com.example.cricket.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cricket.R
import com.example.cricket.model.previewparameter.SeriesInfoPreviewParameterProvider
import com.example.cricket.model.seriesinfo.Data
import com.example.cricket.model.seriesinfo.Match
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SeriesInfoRow(seriesInfoData: Match) {

    Column {

        val dateFmt = SimpleDateFormat("E, MMM dd yyyy", Locale.getDefault())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(modifier = Modifier.padding(
                start = 17.dp,
                top = 5.dp,
                bottom = 3.dp),
                text = dateFmt.format(seriesInfoData.dateTimeGMT)
            )
        }

        Column(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 10.dp,
                    bottom = 8.dp
                ),
            horizontalAlignment = Alignment.Start,
        ) {

            Row {

                val venue = seriesInfoData.venue
                val venuePlace: String = venue.substringAfterLast(",")
                val name = seriesInfoData.name
                val matchNo: String = name.substringAfterLast(",")

                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "$matchNo • $venuePlace" ,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 3.dp)
            ) {


                AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(seriesInfoData.teamInfo[0].img)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(20.dp)
                    .width(40.dp)
                    .clip(shape = RectangleShape)
            )

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp),
                    text = seriesInfoData.teamInfo[0].shortname,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                /*if (data.score.isNotEmpty())*/
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = "",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {

                if (seriesInfoData.teamInfo.size > 1) {
                    //coil
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(seriesInfoData.teamInfo[1].img)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(20.dp)
                            .width(40.dp)
                            .clip(shape = RectangleShape)
                    )

                }else {
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

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp),
                    text = seriesInfoData.teamInfo[1].shortname,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                /*if(data.score.size > 1)*/
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    text = "",
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = seriesInfoData.status,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF55A0C7)
            )

        }
    }

    Divider(modifier = Modifier.fillMaxWidth())

}

@Preview(showBackground = true)
@Composable
fun MatchesRowPreview(@PreviewParameter(SeriesInfoPreviewParameterProvider::class) data: Data){

    SeriesInfoRow(seriesInfoData = data.matchList[0])
}