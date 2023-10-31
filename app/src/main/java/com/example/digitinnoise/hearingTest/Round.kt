package com.example.digitinnoise.hearingTest

interface Round {
    suspend fun play(audioPlayer: AudioPlayer)
}