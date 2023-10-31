package com.example.digitinnoise.hearingTest.domain

interface Digit {
    fun play(audioPlayer: AudioPlayer)
    fun stop()
}