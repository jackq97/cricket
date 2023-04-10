package com.example.cricket.screen.seriesinfoscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricket.repository.CricketRepository
import com.example.cricket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CricketRepository
): ViewModel() {

    var state by mutableStateOf(SeriesInfoState())

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id") ?: return@launch
            state = state.copy(isLoading = true)
            val seriesInfoResult = async { repository.getSeriesInfo(id = id) }
            when (val result = seriesInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        seriesInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        seriesInfo = null
                    )
                }
                else -> Unit
            }

        }
    }
}

