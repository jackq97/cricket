package com.example.cricket.screen.currentMatchesScreen

sealed class CurrentMatchesEvent {
    object Refresh: CurrentMatchesEvent()
}
