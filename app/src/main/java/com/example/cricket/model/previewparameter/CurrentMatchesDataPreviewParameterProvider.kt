package com.example.cricket.model.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.cricket.model.currentmatches.CurrentData
import com.example.cricket.model.currentmatches.Score
import com.example.cricket.model.currentmatches.TeamInfo

class CurrentMatchesDataPreviewParameterProvider : PreviewParameterProvider<CurrentData> {

    override val values = sequenceOf(
        CurrentData(bbbEnabled = false,
            date = "12:00",
            dateTimeGMT = "GMT",
            fantasyEnabled = false,
            hasSquad = true, id = "1",
            matchEnded = false,
            matchStarted = true,
            matchType = "t20",
            name = "india vs pakistan",
            score = listOf(
                Score(inning = "ind", r = 23, o = 12.1, w = 2),
                Score(inning = "pak", r = 200, o = 2.2, w = 4)),
            series_id = "12233m3m3m",
            status = "ind needs 53 runs",
            teamInfo = listOf(
                TeamInfo(name = "india", shortname = "ind", img = "url"),
                TeamInfo(name = "pakistan", shortname = "pak", img = "url")),
            teams = listOf("india","pakistan"),
            venue = "narendrea modi stadium"),
        )
}