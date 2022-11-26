package com.example.cricket.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricket.repository.CricketRepository
import com.example.cricket.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class AllMatchesViewModel @Inject constructor(
    private val cricketRepository: CricketRepository
): ViewModel()  {

    var response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)
    var currentMatchResponse: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getAllMatches()
        getCurrentMatches()
    }

  private fun getAllMatches() = viewModelScope.launch {

        cricketRepository
            .getAllMatches()

            .onStart {

                response.value = ApiState.Loading
            }.catch {

                response.value = ApiState.Failure()
            }.collect {

                response.value = ApiState.Success(it.data) }
    }

    private fun getCurrentMatches() = viewModelScope.launch {

        cricketRepository
            .getCurrentMatches()

            .onStart {

                currentMatchResponse.value = ApiState.Loading
            }.catch {

                currentMatchResponse.value = ApiState.Failure()
            }.collect {

                currentMatchResponse.value = ApiState.Success(it.data) }
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _currentTime = MutableStateFlow(Instant.now())
    val currentTime = _currentTime.asStateFlow()

    fun refresh() = viewModelScope.launch {
        _isRefreshing.update { true }
        getAllMatches()
        _currentTime.value = Instant.now()
        _isRefreshing.update { false }
    }

}