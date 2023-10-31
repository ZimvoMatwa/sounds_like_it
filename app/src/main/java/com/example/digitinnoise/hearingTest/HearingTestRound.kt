package com.example.digitinnoise.hearingTest

import kotlinx.coroutines.delay

class HearingTestRound(private val noise: Noise, private val triplet: Triplet): Round {
    override suspend fun play(audioPlayer: AudioPlayer) {
        noise.play(audioPlayer)
        delay(1000)
        triplet.play(audioPlayer)
        noise.stop()
    }
}