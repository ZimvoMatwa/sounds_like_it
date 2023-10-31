package com.example.digitinnoise.hearingTest.domain

import com.example.digitinnoise.R

class HearingTestNoise(private var difficulty: Int) : Noise {
    private var player: AudioPlayer? = null

    override fun play(audioPlayer: AudioPlayer) {
        player = audioPlayer.create()
        if (difficulty > 10) {
            difficulty = 10
        }

        if (difficulty < 1) {
            difficulty = 1
        }
        player?.play(resourceFromDifficulty(difficulty = difficulty))
    }

    override fun stop() {
        player?.stop()
    }

    private fun resourceFromDifficulty(difficulty: Int): Int {
        return when (difficulty) {
            1 -> R.raw.noise_1
            2 -> R.raw.noise_2
            3 -> R.raw.noise_3
            4 -> R.raw.noise_4
            5 -> R.raw.noise_5
            6 -> R.raw.noise_6
            7 -> R.raw.noise_7
            8 -> R.raw.noise_8
            9 -> R.raw.noise_9
            10 -> R.raw.noise_10
            else -> throw RuntimeException("Invalid difficulty $difficulty")
        }
    }
}