package com.example.cricket.screen.allmatchesscreen

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
class AllMatchesViewModel @Inject constructor(
    private val cricketRepository: CricketRepository
): ViewModel()  {

    var state by mutableStateOf(AllMatchState())

    init {
        getCurrentMatches()
        getAllMatches()
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

    private fun getAllMatches(){

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val allMatchesResult = async { cricketRepository.getAllMatches() }
            when(val result = allMatchesResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        allMatches = result.data,
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

    fun onEvent(event: AllMatchesEvent) {
        when(event) {
            is AllMatchesEvent.Refresh -> {
                getCurrentMatches()
            }
        }
    }

}