package com.example.cricket.screen.seriesscreen/*
package com.example.cricket.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricket.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val cricketRepository: CricketRepository
): ViewModel()  {

    var response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getAllSeries()
    }

  private fun getAllSeries() = viewModelScope.launch {

        cricketRepository
            .getAllSeries()

            .onStart {

                response.value = ApiState.Loading
            }.catch {

                response.value = ApiState.Failure()
            }.collect {

                response.value = ApiState.Success(it.data) }
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _currentTime = MutableStateFlow(Instant.now())
    val currentTime = _currentTime.asStateFlow()

    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        getAllSeries()
        _currentTime.value = Instant.now()
        _isRefreshing.update { false }
    }

}*/
