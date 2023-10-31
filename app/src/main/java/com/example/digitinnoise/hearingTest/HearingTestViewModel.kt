package com.example.digitinnoise.hearingTest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HearingTestViewModel @Inject constructor(
    private val test: Test,
    private val audioPlayer: AudioPlayer
) : ViewModel() {

    private val _uiState: MutableStateFlow<HearingTestViewState> =
        MutableStateFlow(HearingTestViewState.Waiting)
    val uiState: StateFlow<HearingTestViewState> = _uiState
    fun startTest() {
        viewModelScope.launch {
            _uiState.value = HearingTestViewState.Waiting
            delay(3000)
            val round = test.nextRound()
            _uiState.value = HearingTestViewState.Loaded(round)
            round.play(audioPlayer)
        }
    }

    fun submit(answer: String) {
        viewModelScope.launch {
            _uiState.value = HearingTestViewState.Waiting
            delay(2000)
            test.answer(answer)
            val round = test.nextRound()
            _uiState.value = HearingTestViewState.Loaded(round)
            round.play(audioPlayer)
        }
    }
}

sealed class HearingTestViewState {
    object Waiting : HearingTestViewState()
    data class Loaded(val round: Round) : HearingTestViewState()
}
