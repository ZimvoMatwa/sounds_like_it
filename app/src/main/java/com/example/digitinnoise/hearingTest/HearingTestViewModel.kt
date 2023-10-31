package com.example.digitinnoise.hearingTest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HearingTestViewModel(private val test: Test) : ViewModel() {

    private val _uiState: MutableStateFlow<HearingTestViewState> =
        MutableStateFlow(HearingTestViewState.Waiting)
    val uiState: StateFlow<HearingTestViewState> = _uiState

    fun startTest() {
        viewModelScope.launch {
            _uiState.value = HearingTestViewState.Waiting
            delay(3000)
            _uiState.value = HearingTestViewState.Loaded(test.nextRound())
        }
    }

    fun submit() {
        viewModelScope.launch {
            _uiState.value = HearingTestViewState.Waiting
            delay(2000)
            _uiState.value = HearingTestViewState.Loaded(test.nextRound())
        }
    }
}

sealed class HearingTestViewState {
    object Waiting : HearingTestViewState()
    data class Loaded(val round: Round) : HearingTestViewState()
}