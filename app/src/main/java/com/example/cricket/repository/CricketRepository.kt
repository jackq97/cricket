package com.example.cricket.repository

import com.example.cricket.model.allmatches.AllMatches
import com.example.cricket.model.currentmatches.CurrentMatches
import com.example.cricket.model.series.Series
import com.example.cricket.network.MatchesApi
import com.example.cricket.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CricketRepository @Inject constructor(
    private val api: MatchesApi) {

    /*// making an object of our wrapper class
    fun getCurrentMatches(): Flow<CurrentMatches> = flow {
        emit(api.getCurrentMatches())
    }.flowOn(Dispatchers.IO)*/

    suspend fun getCurrentMatches(): Resource<CurrentMatches> {
        return try {
            val result = api.getCurrentMatches()
            Resource.Success(result)
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }
    }

    suspend fun getAllMatches(): Resource<AllMatches> {
        return try {
            val result = api.getAllMatches()
            Resource.Success(result)
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }
    }

    /*// making an object of our wrapper class
    fun getAllMatches(): Flow<AllMatches> = flow {
        emit(api.getAllMatches())
    }.flowOn(Dispatchers.IO)
*/
    // making an object of our wrapper class
    fun getAllSeries(): Flow<Series> = flow {
        emit(api.getAllSeries())
    }.flowOn(Dispatchers.IO)

}