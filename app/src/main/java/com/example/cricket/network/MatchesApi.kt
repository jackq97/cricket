package com.example.cricket.network

import com.example.cricket.model.allmatches.AllMatches
import com.example.cricket.model.currentmatches.CurrentMatches
import com.example.cricket.model.series.Series
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MatchesApi {

    @GET("currentMatches?")
    suspend fun getCurrentMatches(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("offset") offset: Int = OFFSET
    ): CurrentMatches

    //https://api.cricapi.com/v1/matches?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&offset=0
    @GET("matches?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&offset=0")
    suspend fun getAllMatches(): AllMatches

    //https://api.cricapi.com/v1/series?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&offset=0
    @GET("series?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&offset=0")
    suspend fun getAllSeries(): Series

    companion object {
        const val API_KEY = "78d98990-7f82-498f-bec5-4d1bf21e9f28"
        const val BASE_URL = "https://api.cricapi.com/v1/"
        const val OFFSET = 0
    }
}