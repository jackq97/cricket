package com.example.cricket.model.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.cricket.model.seriesinfo.*

class SeriesInfoPreviewParameterProvider : PreviewParameterProvider<Data> {

    override val values = sequenceOf(
        Data(
            info = Info(
                enddate = "12-1-1997",
                id = "123abc",
                matches = 3,
                name = "ind vs pak, 1st Match",
                odi = 1,
                squads = 2,
                startdate = "12-12-1997",
                t20 = 1,
                test = 2
            ),
            matchList = listOf(Match(
                bbbEnabled = false,
                date = "12-12-1909",
                dateTimeGMT = null,
                fantasyEnabled = false,
                hasSquad = false,
                id = "123abc",
                matchEnded = false,
                matchStarted = false,
                matchType = "t20",
                name = "ind vs pak",
                status = "ind won",
                teamInfo = listOf(
                    TeamInfo(img = "", name = "india", shortname = "ind"),
                    TeamInfo(img = "", name = "pakistan", shortname = "pak")),
                teams = listOf("india", "pakistan"),
                venue = "narendra modi stadium, Mumbai"
            )
            )
        )
    )
}
