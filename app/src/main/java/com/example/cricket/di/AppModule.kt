package com.example.cricket.di

import com.example.cricket.network.MatchesApi
import com.example.cricket.repository.CricketRepository
import com.example.cricket.util.GsonDateDeSerializer
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesMatchesRepository(api: MatchesApi) = CricketRepository(api)

    private var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        //.registerTypeAdapter(Date::class.java, GsonDateDeSerializer())
        .create()

    @Singleton
    @Provides
    fun provideQuestionApi(): MatchesApi {
        return Retrofit.Builder()
            .baseUrl(MatchesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MatchesApi::class.java)
    }
}