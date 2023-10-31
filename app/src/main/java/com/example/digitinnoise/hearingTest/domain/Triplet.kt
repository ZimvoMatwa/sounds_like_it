package com.example.digitinnoise.hearingTest.domain

import kotlinx.coroutines.delay

data class Triplet(val digit1: Int, val digit2: Int, val digit3: Int) {
    fun answer(): String {
        return "$digit1$digit2$digit3"
    }

    suspend fun play(audioPlayer: AudioPlayer) {
        val hearingTestDigit1Player = HearingTestDigit(digit1)
        hearingTestDigit1Player.play(audioPlayer)
        delay(1000)
        hearingTestDigit1Player.stop()

        val hearingTestDigit2Player = HearingTestDigit(digit2)
        hearingTestDigit2Player.play(audioPlayer)
        delay(1000)
        hearingTestDigit2Player.stop()

        val hearingTestDigit3Player = HearingTestDigit(digit3)
        hearingTestDigit3Player.play(audioPlayer)
        delay(1000)
        hearingTestDigit3Player.stop()
    }
}