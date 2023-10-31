package com.example.digitinnoise.hearingTest

import kotlinx.coroutines.delay

class HearingTestRound(private val difficulty: Int, private val triplet: Triplet) : Round {
    private var providedAnswer = ""
    override suspend fun play(audioPlayer: AudioPlayer) {
        val noise = HearingTestNoise(difficulty)
        noise.play(audioPlayer)
        delay(1000)
        triplet.play(audioPlayer)
        noise.stop()
    }

    override fun score(): Int {
        return if (answerIsCorrect()) {
            difficulty
        } else {
            0
        }
    }

    override fun answer(answer: String) {
        providedAnswer = answer
    }

    override fun answerIsCorrect(): Boolean {
        return triplet.answer() == providedAnswer
    }
}