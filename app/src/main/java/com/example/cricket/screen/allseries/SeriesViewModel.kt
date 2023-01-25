package com.example.cricket.screen.allseries

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricket.repository.CricketRepository
import com.example.cricket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val cricketRepository: CricketRepository
): ViewModel()  {

    var state by mutableStateOf(SeriesState())

    init {
        getSeries()
    }

    private fun getSeries(){

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val seriesResult = async { cricketRepository.getAllSeries() }
            when(val result = seriesResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        series = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        series = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun onEvent(event: SeriesEvent) {
        when(event) {
            is SeriesEvent.Refresh -> {
                getSeries()
            }
        }
    }
}