package com.example.cricket.repository

import com.example.cricket.model.allmatches.AllMatches
import com.example.cricket.model.currentmatches.CurrentMatches
import com.example.cricket.model.series.Series
import com.example.cricket.network.MatchesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CricketRepository @Inject constructor(
    private val api: MatchesApi) {

    // making an object of our wrapper class
    fun getCurrentMatches(): Flow<CurrentMatches> = flow {
        emit(api.getCurrentMatches())
    }.flowOn(Dispatchers.IO)

    // making an object of our wrapper class
    fun getAllMatches(): Flow<AllMatches> = flow {
        emit(api.getAllMatches())
    }.flowOn(Dispatchers.IO)

    // making an object of our wrapper class
    fun getAllSeries(): Flow<Series> = flow {
        emit(api.getAllSeries())
    }.flowOn(Dispatchers.IO)

}