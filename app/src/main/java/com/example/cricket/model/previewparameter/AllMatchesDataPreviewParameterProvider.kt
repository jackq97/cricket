package com.example.cricket.model.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.cricket.model.allmatches.AllData
import com.example.cricket.model.allmatches.TeamInfo

class AllMatchesDataPreviewParameterProvider : PreviewParameterProvider<AllData> {

    override val values = sequenceOf(
        AllData(bbbEnabled = false,
            date = "11 OCT",
            dateTimeGMT = null,
            fantasyEnabled = false,
            hasSquad = true, id = "1",
            matchEnded = false,
            matchStarted = false,
            matchType = "t20",
            name = "india vs pakistan",
            series_id = "12233m3m3m",
            status = "ind needs 53 runs",
            teamInfo = listOf(
                TeamInfo(name = "india", shortname = "ind", img = "url"),
                TeamInfo(name = "pakistan", shortname = "pak", img = "url")),
            teams = listOf("india","pakistan"),
            venue = "narendrea modi stadium"),
        )
}
