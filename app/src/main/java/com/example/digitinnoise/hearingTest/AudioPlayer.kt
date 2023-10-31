package com.example.digitinnoise.hearingTest

interface AudioPlayer {
    fun create(): AudioPlayer
    fun play(resource: Int)
    fun stop()
}