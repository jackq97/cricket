package com.example.cricket.model.seriesinfo

data class MatchInfo(
    val enddate: String,
    val id: String,
    val matches: Int,
    val name: String,
    val odi: Int,
    val squads: Int,
    val startdate: String,
    val t20: Int,
    val test: Int
)