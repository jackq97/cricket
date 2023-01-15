package com.example.cricket.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.cricket.model.allmatches.AllData
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AllMatchesRow(allMatchesData: AllData) {

    Card(
            modifier = Modifier
                .width(380.dp)
                .height(110.dp)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(22.dp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 10.dp
        ) {

            Column(
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                    Text(text = if(allMatchesData.teamInfo != null && allMatchesData.teamInfo[0].shortname != null)
                    {allMatchesData.teamInfo[0].shortname}
                        else{allMatchesData.teams[0]},
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        maxLines = 1
                    )


                    //coil
                    if(allMatchesData.teamInfo != null)
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(allMatchesData.teamInfo[0].img)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(30.dp)
                                .width(40.dp)
                                .clip(shape = CircleShape)
                        )
                    else
                        Image(
                        painter = painterResource(id = R.drawable.fl_default),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(30.dp)
                            .width(40.dp)
                            .clip(shape = CircleShape)
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val timeFmt = SimpleDateFormat("HH:mm", Locale.getDefault())

                        Text(
                            text = timeFmt.format(allMatchesData.dateTimeGMT),
                            modifier = Modifier.padding(bottom = 8.dp),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE5493E),
                            fontSize = 10.sp
                        )

                        val dateFmt = SimpleDateFormat("dd MMM", Locale.getDefault())

                        Text(
                            text = dateFmt.format(allMatchesData.dateTimeGMT),
                            color = Color(0xFFA7A6AB),
                            fontSize = 13.sp
                        )
                    }

                    if (allMatchesData.teamInfo != null && allMatchesData.teamInfo.size > 1){
                        //coil
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(allMatchesData.teamInfo[1].img)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(30.dp)
                                .width(40.dp)
                                .clip(shape = CircleShape)
                        )
                    } else {

                        Image(
                            painter = painterResource(id = R.drawable.fl_default),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(30.dp)
                                .width(40.dp)
                                .clip(shape = CircleShape)
                        )
                    }

                    Text(
                        text = if(allMatchesData.teamInfo != null &&
                            allMatchesData.teamInfo.size > 1 &&
                            allMatchesData.teamInfo[1].shortname != null){allMatchesData.teamInfo[1].shortname}
                        else{allMatchesData.teams[1]},
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp)
                            .align(Alignment.CenterVertically),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                    )

                }

                Text(
                    text = allMatchesData.venue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
