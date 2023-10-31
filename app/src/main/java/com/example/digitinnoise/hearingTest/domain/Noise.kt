package com.example.digitinnoise.hearingTest.domain

interface Noise {
    fun play(audioPlayer: AudioPlayer)
    fun stop()
}