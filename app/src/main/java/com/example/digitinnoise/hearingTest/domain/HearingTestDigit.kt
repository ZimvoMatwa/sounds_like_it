package com.example.digitinnoise.hearingTest.domain

import com.example.digitinnoise.R

class HearingTestDigit(private val digit: Int) : Digit {
    private var player: AudioPlayer? = null

    override fun play(audioPlayer: AudioPlayer) {
        player = audioPlayer.create()
        player?.play(resourceFromInt(digit))
    }

    override fun stop() {
        player?.stop()
    }

    private fun resourceFromInt(digit: Int): Int {
        return when (digit) {
            1 -> R.raw.number_1
            2 -> R.raw.number_2
            3 -> R.raw.number_3
            4 -> R.raw.number_4
            5 -> R.raw.number_5
            6 -> R.raw.number_6
            7 -> R.raw.number_7
            8 -> R.raw.number_8
            9 -> R.raw.number_9
            else -> throw Exception("Invalid digit")
        }
    }
}