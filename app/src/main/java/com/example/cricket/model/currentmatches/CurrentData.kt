package com.example.cricket.model.currentmatches

import java.util.Date

data class CurrentData(
    var bbbEnabled: Boolean,
    var date: String,
    var dateTimeGMT: Date?,
    var fantasyEnabled: Boolean,
    var hasSquad: Boolean,
    var id: String,
    var matchEnded: Boolean,
    var matchStarted: Boolean,
    var matchType: String,
    var name: String,
    var score: List<Score>,
    var series_id: String,
    var status: String,
    var teamInfo: List<TeamInfo>,
    var teams: List<String>,
    var venue: String
)