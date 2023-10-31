package com.example.digitinnoise.hearingTest

interface Round {
    suspend fun play(audioPlayer: AudioPlayer)
    fun score(): Int
    fun answer(answer: String)
     fun answerIsCorrect(): Boolean
}