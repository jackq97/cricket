package com.example.cricket.network

import com.example.cricket.model.allmatches.AllMatches
import com.example.cricket.model.currentmatches.CurrentMatches
import com.example.cricket.model.series.Series
import com.example.cricket.model.seriesinfo.SeriesInfo
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
    @GET("matches?")
    suspend fun getAllMatches(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("offset") offset: Int = OFFSET
    ): AllMatches

    //https://api.cricapi.com/v1/series?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&offset=0
    @GET("series?")
    suspend fun getAllSeries(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("offset") offset: Int = OFFSET
    ): Series

    //https://api.cricapi.com/v1/series_info?apikey=78d98990-7f82-498f-bec5-4d1bf21e9f28&id=56b8f209-0268-4c17-afe5-bb3c4c41c167
    @GET("series_info?")
    suspend fun getSeriesInfo(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("id") id: String
    ): SeriesInfo

    companion object {
        const val API_KEY = ""
        const val BASE_URL = "https://api.cricapi.com/v1/"
        const val OFFSET = 0
    }
}