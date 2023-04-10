package com.example.cricket.model.seriesinfo

import java.util.*

data class Match(
    val bbbEnabled: Boolean,
    val date: String,
    val dateTimeGMT: Date?,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchEnded: Boolean,
    val matchStarted: Boolean,
    val matchType: String,
    val name: String,
    val status: String,
    val teamInfo: List<TeamInfo>,
    val teams: List<String>,
    val venue: String
)