package com.example.cricket.ui.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cricket.R
import com.example.cricket.model.currentmatches.CurrentData

@Composable
fun RecentMatchesRow(data: CurrentData) {

    if(data.matchEnded) {
        Column(
            modifier = Modifier
                .height(128.dp)
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = 8.dp
                ),
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                text = "${data.matchType} • ${data.name}",
                fontWeight = FontWeight.Light,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .padding(top = 3.dp)
            ) {

                if (data.teamInfo != null)
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

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp),
                    text = if (data.teamInfo != null && data.teamInfo[0].shortname != null) {
                        data.teamInfo[0].shortname
                    } else {
                        data.teams[0]
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (data.score.isNotEmpty())
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        text = "${data.score[0].r}-${data.score[0].w} (${data.score[0].o})",
                        fontWeight = FontWeight.Bold
                    )
            }

            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {

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

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 3.dp),
                    text = if (data.teamInfo != null &&
                         data.teamInfo.size > 1 &&
                        data.teamInfo[1].shortname != null) {
                        data.teamInfo[1].shortname
                    } else {
                        data.teams[1]
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (data.score.size > 1)
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        text = "${data.score[1].r}-${data.score[1].w} (${data.score[1].o})",
                        fontWeight = FontWeight.Bold
                    )
            }

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = data.status,
                color = Color(0xFF7AC4EB)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    fontSize = 11.sp,
                    text = "SCHEDULE"
                )
            }
        }

        Divider(modifier = Modifier.fillMaxWidth())
    }

}
