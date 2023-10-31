package com.example.digitinnoise.hearingTest

interface Noise {
    fun play(audioPlayer: AudioPlayer)
    fun stop()
}