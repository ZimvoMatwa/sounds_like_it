package com.example.digitinnoise

import com.example.digitinnoise.hearingTest.AudioPlayer

interface Digit {
    fun play(audioPlayer: AudioPlayer)
    fun stop()
}