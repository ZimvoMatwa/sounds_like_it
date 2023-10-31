package com.example.digitinnoise.hearingTest

import kotlinx.coroutines.delay

data class Triplet(val digit1: Int, val digit2: Int, val digit3: Int) {
    fun answer(): String {
        return "$digit1$digit2$digit3"
    }

    suspend fun play(audioPlayer: AudioPlayer) {
        val digit1Player = Digit(digit1)
        digit1Player.play(audioPlayer)
        delay(1000)
        digit1Player.stop()

        val digit2Player = Digit(digit2)
        digit2Player.play(audioPlayer)
        delay(1000)
        digit2Player.stop()

        val digit3player = Digit(digit3)
        digit3player.play(audioPlayer)
        delay(1000)
        digit3player.stop()
    }
}