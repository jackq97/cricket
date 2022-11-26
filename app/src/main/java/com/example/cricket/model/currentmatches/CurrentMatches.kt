package com.example.cricket.model.currentmatches

data class CurrentMatches(
    val apikey: String,
    val data: List<CurrentData>,
    val info: Info,
    val status: String
)