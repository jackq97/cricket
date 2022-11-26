package com.example.cricket.model.allmatches

data class AllMatches(
    val apikey: String,
    val data: List<AllData>,
    val info: Info,
    val status: String
)