package com.example.digitinnoise.hearingTest.domain

interface AudioPlayer {
    fun create(): AudioPlayer
    fun play(resource: Int)
    fun stop()
}