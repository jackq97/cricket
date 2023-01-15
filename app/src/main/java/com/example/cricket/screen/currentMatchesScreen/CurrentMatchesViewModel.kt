package com.example.cricket.screen.currentMatchesScreen

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
class CurrentMatchesViewModel @Inject constructor(
    private val cricketRepository: CricketRepository
): ViewModel()  {

    var state by mutableStateOf(CurrentMatchState())

    init {
        getCurrentMatches()
    }

    private fun getCurrentMatches(){

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val currentMatchesResult = async { cricketRepository.getCurrentMatches() }
            when(val result = currentMatchesResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        currentMatches = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        currentMatches = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun onEvent(event: CurrentMatchesEvent) {
        when(event) {
            is CurrentMatchesEvent.Refresh -> {
                getCurrentMatches()
            }
        }
    }
}