package com.example.digitinnoise.hearingTest

import com.example.digitinnoise.R

class HearingTestNoise : Noise {
    private var player: AudioPlayer? = null

    override fun play(audioPlayer: AudioPlayer) {
        player = audioPlayer.create()
        player?.play(R.raw.noise_1)
    }

    override fun stop() {
        player?.stop()
    }
}